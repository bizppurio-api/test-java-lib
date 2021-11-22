package main.java.bizapiLib.action.resend;

import com.google.gson.JsonObject;

public class LMSResend extends Resend {

    public LMSResend message(String message) {
        recontentDetail.addProperty("message", message);
        return this;
    }

    public LMSResend subject(String subject) {
        recontentDetail.addProperty("subject", subject);
        return this;
    }

    public LMSResend() {
        type = "lms";
    }

    public JsonObject validation(JsonObject requestBody) {
        requiredFieldByType.add("recontent.lms.message");
        maxFiledByType.put("recontent.lms.message", 2000);
        maxFiledByType.put("recontent.lms.subject", 64);

        return super.validation(requestBody);
    }
}
