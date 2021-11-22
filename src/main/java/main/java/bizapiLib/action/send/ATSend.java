package main.java.bizapiLib.action.send;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.action.resend.Resend;
import main.java.bizapiLib.bizFacotry.attachment.kakao.KakaoButton;
import main.java.bizapiLib.common.BizapiDefine;
import main.java.bizapiLib.common.BizapiUtil;
import main.java.bizapiLib.response.MessageResponse;

public class ATSend extends Send<ATSend, MessageResponse> {
    public ATSend(TokenIssuer tokenIssuer) {
        super(tokenIssuer, "at");
        content.add("at", contentDetail);
    }

    public ATSend message(String message) {
        contentDetail.addProperty("message", message);
        return this;
    }

    public ATSend senderkey(String senderkey) {
        contentDetail.addProperty("senderkey", senderkey);
        return this;
    }

    public ATSend templatecode(String templatecode) {
        contentDetail.addProperty("templatecode", templatecode);
        return this;
    }

    public ATSend button(KakaoButton... buttons) {
        JsonArray array = new JsonArray();
        for (KakaoButton button : buttons) {
            array.add(button.getButton());
        }
        contentDetail.add("button", array);
        return this;
    }

    public ATSend title(String tile) {
        contentDetail.addProperty("tile", tile);
        return this;
    }

    public ATSend quickreply(String quickreply) {
        contentDetail.addProperty("quickreply", quickreply);
        return this;
    }

    public ATSend resendFirst(Resend first) {
        return super.resendFirst(first);
    }

    public ATSend resendSecond(Resend second) {
        return super.resendSecond(second);
    }

    @SneakyThrows
    @Override
    protected JsonObject validation() {
        JsonObject response = BizapiUtil.maxLength(requestBody, "content.at.message", 1000, requestBody.get("refkey"));
        if (response != null) return response;

        JsonElement button = BizapiUtil.divideKey(requestBody, "content.at.button");
        if (button != null) {
            if (!button.isJsonArray()) {
                return BizapiUtil.makeResponse(BizapiDefine.INVALID_MESSAGE.getCode(), "at.button format is an array.", requestBody.get("refkey"));
            }

            for (JsonElement jsonElement : button.getAsJsonArray()) {
                JsonElement name = jsonElement.getAsJsonObject().get("name");
                if (name != null && name.getAsString().getBytes("EUC-KR").length > 28) {
                    response = BizapiUtil.makeResponse(BizapiDefine.INVALID_MESSAGE.getCode(), "The at.button.name may not be greater than 28 characters.", requestBody.get("refkey"));
                }
                if (response != null) return response;
            }
        }

        requiredFieldByType.add("content.at.message");
        requiredFieldByType.add("content.at.senderkey");
        requiredFieldByType.add("content.at.templatecode");
        maxFiledByType.put("content.at.senderkey", 40);
        maxFiledByType.put("content.at.templatecode", 32);

        response = super.validation();
        if (response != null) return response;

       return resendValidation(requestBody);
    }
}
