package main.java.bizapiLib.action.resend;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import main.java.bizapiLib.bizFacotry.attachment.kakao.KakaoButton;
import main.java.bizapiLib.common.BizapiDefine;
import main.java.bizapiLib.common.BizapiUtil;

public class ATResend extends Resend {
    public ATResend message(String message) {
        recontentDetail.addProperty("message", message);
        return this;
    }

    public ATResend() {
        type = "at";
    }

    public ATResend senderkey(String senderkey) {
        recontentDetail.addProperty("senderkey", senderkey);
        return this;
    }

    public ATResend templatecode(String templatecode) {
        recontentDetail.addProperty("templatecode", templatecode);
        return this;
    }

    public ATResend button(KakaoButton... buttons) {
        JsonArray array = new JsonArray();
        for (KakaoButton button : buttons) {
            array.add(button.getButton());
        }
        recontentDetail.add("button", array);
        return this;
    }

    public ATResend tile(String tile) {
        recontentDetail.addProperty("tile", tile);
        return this;
    }

    public ATResend quickreply(String quickreply) {
        recontentDetail.addProperty("quickreply", quickreply);
        return this;
    }

    @SneakyThrows
    public JsonObject validation(JsonObject requestBody) {
        JsonObject response = BizapiUtil.maxLength(requestBody, "recontent.at.message", 1000, requestBody.get("refkey"));
        if (response != null) return response;

        JsonElement button = BizapiUtil.divideKey(requestBody, "recontent.at.button");
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

        requiredFieldByType.add("recontent.at.message");
        requiredFieldByType.add("recontent.at.senderkey");
        requiredFieldByType.add("recontent.at.templatecode");
        maxFiledByType.put("recontent.at.senderkey", 40);
        maxFiledByType.put("recontent.at.templatecode", 32);

        return super.validation(requestBody);
    }
}
