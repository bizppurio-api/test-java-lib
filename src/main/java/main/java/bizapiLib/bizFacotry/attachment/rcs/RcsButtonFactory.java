package main.java.bizapiLib.bizFacotry.attachment.rcs;

public class RcsButtonFactory {
    public OpenurlAction openUrlAction(String displayText) {
        return new OpenurlAction(displayText);
    }

    public dialPhoneNumberAction createDialerAction(String displayText) {
        return new dialPhoneNumberAction(displayText);
    }

    public ShowLocationAction createShowLocationAction(String displayText) {
        return new ShowLocationAction(displayText);
    }

    public RequestLocationPushAction createRequestLocationPushAction(String displayText) {
        return new RequestLocationPushAction(displayText);
    }

    public CreateCalendarEventAction createCalendarEventAction(String displayText) {
        return new CreateCalendarEventAction(displayText);
    }

    public ComposeTextMessageAction createComposeTextMessageAction(String displayText) {
        return new ComposeTextMessageAction(displayText);
    }

    public CopyToClipboardAction createCopyToClipboardAction(String displayText) {
        return new CopyToClipboardAction(displayText);
    }

    public RcsAction[] createSuggestions(RcsAction... action) {
        return action;
    }
}
