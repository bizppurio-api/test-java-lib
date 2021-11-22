package main.java.bizapiLib.action.resend;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import main.java.bizapiLib.common.BizapiDefine;
import main.java.bizapiLib.common.BizapiUtil;

public class FTResend extends Resend {

    public FTResend message(String message) {
        recontentDetail.addProperty("message", message);
        return this;
    }

    public FTResend() {
        type = "ft";
    }

    public FTResend senderkey(String senderkey) {
        recontentDetail.addProperty("senderkey", senderkey);
        return this;
    }

    public FTResend userkey(String userkey) {
        recontentDetail.addProperty("userkey", userkey);
        return this;
    }

    public FTResend adflag(String adflag) {
        recontentDetail.addProperty("adflag", adflag);
        return this;
    }

    public FTResend button(JsonArray button) {
        recontentDetail.add("button", button);
        return this;
    }

    public FTResend image(JsonObject image) {
        recontentDetail.add("image", image);
        return this;
    }

    public FTResend wide(String wide) {
        recontentDetail.addProperty("wide", wide);
        return this;
    }

    @SneakyThrows
    public JsonObject validation(JsonObject requestBody) {
        JsonObject response = BizapiUtil.maxLength(requestBody, "recontent.ft.message", 1000, requestBody.get("refkey"));
        if (response != null) return response;

        maxFiledByType.put("recontent.ft.senderkey", 40);

        JsonElement button = BizapiUtil.divideKey(requestBody, "recontent.ft.button");
        if (button != null) {
            if (!button.isJsonArray()) {
                return BizapiUtil.makeResponse(BizapiDefine.INVALID_MESSAGE.getCode(), "ft.button format is an array.", requestBody.get("refkey"));
            }

            for (JsonElement jsonElement : button.getAsJsonArray()) {
                JsonElement name = jsonElement.getAsJsonObject().get("name");
                if (name != null && name.getAsString().getBytes("EUC-KR").length > 28) {
                    response = BizapiUtil.makeResponse(BizapiDefine.INVALID_MESSAGE.getCode(), "The ft.button.name may not be greater than 28 characters.", requestBody.get("refkey"));
                }
                if (response != null) return response;
            }
        }

        requiredFieldByType.add("recontent.ft.message");
        requiredFieldByType.add("recontent.ft.senderkey");
        maxFiledByType.put("recontent.ft.senderkey", 40);

        return super.validation(requestBody);
    }
}
