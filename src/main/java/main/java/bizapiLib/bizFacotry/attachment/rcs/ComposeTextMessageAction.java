package main.java.bizapiLib.bizFacotry.attachment.rcs;

import com.google.gson.JsonObject;

public class ComposeTextMessageAction extends RcsAction {
    JsonObject composeAction = new JsonObject();
    JsonObject composeTextMessage = new JsonObject();

    public ComposeTextMessageAction(String displayText) {
        super(displayText);
        action.add("composeAction", composeAction);
        composeAction.add("composeTextMessage", composeTextMessage);
    }

    public ComposeTextMessageAction phoneNumber(String phoneNumber) {
        composeTextMessage.addProperty("phoneNumber", phoneNumber);
        return this;
    }

    public ComposeTextMessageAction text(String text) {
        composeTextMessage.addProperty("text", text);
        return this;
    }
}
