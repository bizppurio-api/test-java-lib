package main.java.bizapiLib.action.send;

import com.google.gson.JsonObject;
import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.response.MessageResponse;

public class LMSSend extends Send<LMSSend, MessageResponse> {
    public LMSSend(TokenIssuer tokenIssuer) {
        super(tokenIssuer, "lms");
        content.add("lms", contentDetail);
    }

    public LMSSend message(String message) {
        contentDetail.addProperty("message", message);
        return this;
    }

    public LMSSend subject(String subject) {
        contentDetail.addProperty("subject", subject);
        return this;
    }

    @Override
    protected JsonObject validation() {
        requiredFieldByType.add("content.lms.message");
        maxFiledByType.put("content.lms.message", 2000);
        maxFiledByType.put("content.lms.subject", 64);

        return super.validation();
    }
}
