package main.java.bizapiLib.bizFacotry.attachment.rcs;

import com.google.gson.JsonObject;

public class CopyToClipboardAction extends RcsAction {
    JsonObject clipboardAction = new JsonObject();
    JsonObject copyToClipboard = new JsonObject();

    public CopyToClipboardAction(String displayText) {
        super(displayText);
        action.add("clipboardAction", clipboardAction);
        clipboardAction.add("copyToClipboard", copyToClipboard);
    }

    public CopyToClipboardAction text(String text) {
        copyToClipboard.addProperty("text", text);
        return this;
    }
}
