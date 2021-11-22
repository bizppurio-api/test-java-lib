package main.java.bizapiLib.action.send;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.action.resend.Resend;
import main.java.bizapiLib.bizFacotry.attachment.rcs.RcsAction;
import main.java.bizapiLib.common.RcsMessage;
import main.java.bizapiLib.response.MessageResponse;

public class RCSSend extends Send<RCSSend, MessageResponse> {
    public RCSSend(TokenIssuer tokenIssuer) {
        super(tokenIssuer, "rcs");
        content.add("rcs", contentDetail);
    }

    public RCSSend message(RcsMessage... rcsMessages) {
        JsonObject message = new JsonObject();
        if (rcsMessages.length == 1) {
            rcsMessages[0].setMessage(message);
        } else {
            for (int i = 0; i < rcsMessages.length; i++) {
                rcsMessages[i].setMessage(message, i + 1);
            }
        }
        contentDetail.add("message", message);

        return this;
    }

    public RCSSend messagebaseid(String messagebaseid) {
        contentDetail.addProperty("messagebaseid", messagebaseid);
        return this;
    }

    public RCSSend chatbotid(String chatbotid) {
        contentDetail.addProperty("chatbotid", chatbotid);
        return this;
    }

    public RCSSend agencyid(String agencyid) {
        contentDetail.addProperty("agencyid", agencyid);
        return this;
    }

    public RCSSend header(String header) {
        contentDetail.addProperty("header", header);
        return this;
    }

    public RCSSend footer(String footer) {
        contentDetail.addProperty("footer", footer);
        return this;
    }

    public RCSSend copyallowed(String copyallowed) {
        contentDetail.addProperty("copyallowed", copyallowed);
        return this;
    }

    public RCSSend button(RcsAction[]... rcsActions) {
        JsonArray button = new JsonArray();
        JsonArray actionList;
        JsonObject action;
        for (RcsAction[] s : rcsActions) {
            JsonObject suggestions = new JsonObject();
            actionList = new JsonArray();
            for (RcsAction a : s) {
                action = new JsonObject();
                action.add("action", a.getAction());
                actionList.add(action);
            }
            if (actionList.size() > 0)
                suggestions.add("suggestions", actionList);
            button.add(suggestions);
        }

        contentDetail.add("button", button);
        return this;
    }

    public RCSSend resendFirst(Resend first) {
        return super.resendFirst(first);
    }

    public RCSSend resendSecond(Resend second) {
        return super.resendSecond(second);
    }

    @Override
    protected JsonObject validation() {
        requiredFieldByType.add("content.rcs.messagebaseid");
        requiredFieldByType.add("content.rcs.message");
        requiredFieldByType.add("content.rcs.chatbotid");
        requiredFieldByType.add("content.rcs.header");
        maxFiledByType.put("content.rcs.messagebaseid", 40);
        maxFiledByType.put("content.rcs.chatbotid", 40);
        maxFiledByType.put("content.rcs.header", 1);

        JsonElement header = contentDetail.get("header");
        if (header != null && "1".equals(header.getAsString())) {
            requiredFieldByType.add("content.rcs.footer");
            maxFiledByType.put("content.rcs.footer", 64);
        }

        JsonObject response = super.validation();
        if (response != null) return response;

        return resendValidation(requestBody);
    }
}
