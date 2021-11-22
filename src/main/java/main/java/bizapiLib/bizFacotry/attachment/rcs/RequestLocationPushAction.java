package main.java.bizapiLib.bizFacotry.attachment.rcs;

import com.google.gson.JsonObject;

public class RequestLocationPushAction extends RcsAction {
    JsonObject mapAction = new JsonObject();

    public RequestLocationPushAction(String displayText) {
        super(displayText);
        action.add("mapAction", mapAction);
        mapAction.add("requestLocationPush", new JsonObject());
    }
}
