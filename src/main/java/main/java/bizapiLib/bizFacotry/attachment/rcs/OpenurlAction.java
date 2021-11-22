package main.java.bizapiLib.bizFacotry.attachment.rcs;

import com.google.gson.JsonObject;

public class OpenurlAction extends RcsAction {
    JsonObject urlAction = new JsonObject();
    JsonObject openUrl = new JsonObject();

    public OpenurlAction(String displayText) {
        super(displayText);
        action.add("urlACtion", urlAction);
        urlAction.add("openUrl", openUrl);
    }

    public OpenurlAction url(String url) {
        openUrl.addProperty("url", url);
        return this;
    }

}
