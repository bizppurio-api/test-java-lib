package main.java.bizapiLib.action.send;

import com.google.gson.JsonObject;
import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.response.MessageResponse;

public class SMSSend extends Send<SMSSend, MessageResponse> {
    public SMSSend(TokenIssuer tokenIssuer) {
        super(tokenIssuer, "sms");
        content.add("sms", contentDetail);
    }

    public SMSSend message(String message) {
        contentDetail.addProperty("message", message);
        return this;
    }

    @Override
    protected JsonObject validation() {
        requiredFieldByType.add("content.sms.message");
        maxFiledByType.put("content.sms.message", 90);

        return super.validation();
    }
}
