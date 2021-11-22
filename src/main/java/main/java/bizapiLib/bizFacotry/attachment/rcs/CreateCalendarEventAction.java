package main.java.bizapiLib.bizFacotry.attachment.rcs;

import com.google.gson.JsonObject;

public class CreateCalendarEventAction extends RcsAction {
    JsonObject calendarAction = new JsonObject();
    JsonObject createCalendarEvent = new JsonObject();

    public CreateCalendarEventAction(String displayText) {
        super(displayText);
        action.add("calendarAction", calendarAction);
        calendarAction.add("createCalendarEvent", createCalendarEvent);
    }

    public CreateCalendarEventAction startTime(String startTime) {
        createCalendarEvent.addProperty("startTime", startTime);
        return this;
    }

    public CreateCalendarEventAction endTime(String endTime) {
        createCalendarEvent.addProperty("endTime", endTime);
        return this;
    }

    public CreateCalendarEventAction title(String title) {
        createCalendarEvent.addProperty("title", title);
        return this;
    }

    public CreateCalendarEventAction description(String description) {
        createCalendarEvent.addProperty("description", description);
        return this;
    }
}
