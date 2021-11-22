package main.java.bizapiLib.bizFacotry.attachment.rcs;

import com.google.gson.JsonObject;

public class ShowLocationAction extends RcsAction {
    JsonObject mapAction = new JsonObject();
    JsonObject showLocation = new JsonObject();
    JsonObject location = new JsonObject();

    public ShowLocationAction(String displayText) {
        super(displayText);
        action.add("mapAction", mapAction);
        mapAction.add("showLocation", showLocation);
        showLocation.add("location", location);
    }

    public ShowLocationAction latitude(double latitude) {
        location.addProperty("latitude", latitude);
        return this;
    }

    public ShowLocationAction longitude(double longitude) {
        location.addProperty("longitude", longitude);
        return this;
    }

    public ShowLocationAction query(String query) {
        location.addProperty("query", query);
        return this;
    }

    public ShowLocationAction label(String label) {
        location.addProperty("label", label);
        return this;
    }

    public ShowLocationAction fallbackUrl(String fallbackUrl) {
        showLocation.addProperty("fallbackUrl", fallbackUrl);
        return this;
    }
}
