package main.java.bizapiLib.bizFacotry.attachment.rcs;

import com.google.gson.JsonObject;
import lombok.Getter;

public class RcsAction {
    @Getter
    JsonObject action = new JsonObject();

    public RcsAction(String displayText) {
        action.addProperty("displayText", displayText);
    }

    @Override
    public String toString() {
        return action.toString();
    }
}
