package main.java.bizapiLib.action.resend;

import com.google.gson.JsonObject;

public class SMSResend extends Resend {
    
    public SMSResend message(String message) {
        recontentDetail.addProperty("message", message);
        return this;
    }

    public SMSResend() {
        type = "sms";
    }

    public JsonObject validation(JsonObject requestBody) {
        requiredFieldByType.add("recontent.sms.message");
        maxFiledByType.put("recontent.sms.message", 90);

        return super.validation(requestBody);
    }
}
