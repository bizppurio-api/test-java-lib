package main.java.bizapiLib.bizFacotry.attachment.rcs;

import com.google.gson.JsonObject;

public class dialPhoneNumberAction extends RcsAction {
    JsonObject dialerAction = new JsonObject();
    JsonObject dialPhoneNumber = new JsonObject();

    public dialPhoneNumberAction(String displayText) {
        super(displayText);
        action.add("dialPhoneNumberAction", dialerAction);
        dialerAction.add("dialPhoneNumber", dialPhoneNumber);
    }

    public dialPhoneNumberAction phoneNumber(String phoneNumber) {
        dialPhoneNumber.addProperty("phoneNumber", phoneNumber);
        return this;
    }
}
