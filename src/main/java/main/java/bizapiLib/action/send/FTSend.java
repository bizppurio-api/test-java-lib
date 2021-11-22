package main.java.bizapiLib.action.send;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.action.resend.Resend;
import main.java.bizapiLib.bizFacotry.attachment.kakao.KakaoButton;
import main.java.bizapiLib.bizFacotry.attachment.kakao.KakaoImage;
import main.java.bizapiLib.common.BizapiDefine;
import main.java.bizapiLib.common.BizapiUtil;
import main.java.bizapiLib.response.MessageResponse;

public class FTSend extends Send<FTSend, MessageResponse> {
    JsonObject recontent;
    JsonObject resend;

    public FTSend(TokenIssuer tokenIssuer) {
        super(tokenIssuer, "ft");
        content.add("ft", contentDetail);
    }

    public FTSend message(String message) {
        contentDetail.addProperty("message", message);
        return this;
    }

    public FTSend senderkey(String senderkey) {
        contentDetail.addProperty("senderkey", senderkey);
        return this;
    }

    public FTSend userkey(String userkey) {
        contentDetail.addProperty("userkey", userkey);
        return this;
    }

    public FTSend adflag(String adflag) {
        contentDetail.addProperty("adflag", adflag);
        return this;
    }

    public FTSend button(KakaoButton... buttons) {
        JsonArray array = new JsonArray();
        for (KakaoButton button : buttons) {
            array.add(button.getButton());
        }
        contentDetail.add("button", array);
        return this;
    }

    public FTSend image(KakaoImage image) {
        contentDetail.add("image", image.getImage());
        return this;
    }

    public FTSend wide(String wide) {
        contentDetail.addProperty("wide", wide);
        return this;
    }

    public FTSend resendFirst(Resend first) {
        return super.resendFirst(first);
    }

    public FTSend resendSecond(Resend second) {
        return super.resendSecond(second);
    }

    @SneakyThrows
    @Override
    protected JsonObject validation() {
        JsonObject response = BizapiUtil.maxLength(requestBody, "content.ft.message", 1000, requestBody.get("refkey"));
        if (response != null) return response;

        JsonElement button = BizapiUtil.divideKey(requestBody, "content.ft.button");
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

        requiredFieldByType.add("content.ft.message");
        requiredFieldByType.add("content.ft.senderkey");
        maxFiledByType.put("content.ft.senderkey", 40);

        response = super.validation();
        if (response != null) return response;

        return resendValidation(requestBody);
    }
}
