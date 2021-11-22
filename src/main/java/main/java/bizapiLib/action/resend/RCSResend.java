package main.java.bizapiLib.action.resend;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import main.java.bizapiLib.common.RcsMessage;

public class RCSResend extends Resend {
    public RCSResend() {
        type = "rcs";
    }

    public RCSResend message(RcsMessage... rcsMessages) {
        JsonObject message = new JsonObject();
        if (rcsMessages.length == 1) {
            rcsMessages[0].setMessage(message);
        } else {
            for (int i = 0; i < rcsMessages.length; i++) {
                rcsMessages[i].setMessage(message, i + 1);
            }
        }
        recontentDetail.add("message", message);

        return this;
    }

    public RCSResend messagebaseid(String messagebaseid) {
        recontentDetail.addProperty("messagebaseid", messagebaseid);
        return this;
    }

    public RCSResend chatbotid(String chatbotid) {
        recontentDetail.addProperty("chatbotid", chatbotid);
        return this;
    }

    public RCSResend agencyid(String agencyid) {
        recontentDetail.addProperty("agencyid", agencyid);
        return this;
    }

    public RCSResend header(String header) {
        recontentDetail.addProperty("header", header);
        return this;
    }

    public RCSResend footer(String footer) {
        recontentDetail.addProperty("footer", footer);
        return this;
    }

    public RCSResend copyallowed(String copyallowed) {
        recontentDetail.addProperty("copyallowed", copyallowed);
        return this;
    }

    public RCSResend button(JsonArray button) {
        recontentDetail.add("button", button);
        return this;
    }

    public JsonObject validation(JsonObject requestBody) {
        requiredFieldByType.add("recontent.rcs.messagebaseid");
        requiredFieldByType.add("recontent.rcs.message");
        requiredFieldByType.add("recontent.rcs.chatbotid");
        requiredFieldByType.add("recontent.rcs.header");
        maxFiledByType.put("recontent.rcs.messagebaseid", 40);
        maxFiledByType.put("recontent.rcs.chatbotid", 40);
        maxFiledByType.put("recontent.rcs.header", 1);

        JsonElement header = recontentDetail.get("header");
        if (header != null && "1".equals(header.getAsString())) {
            requiredFieldByType.add("recontent.rcs.footer");
            maxFiledByType.put("recontent.rcs.footer", 64);
        }

        return super.validation(requestBody);
    }
}
