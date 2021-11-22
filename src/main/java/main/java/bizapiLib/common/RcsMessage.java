package main.java.bizapiLib.common;

import com.google.gson.JsonObject;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class RcsMessage {
    String title;
    String description;
    String media;
    Map<String, String> variable = new HashMap<>();

    public RcsMessage title(String title) {
        this.title = title;
        return this;
    }

    public RcsMessage description(String description) {
        this.description = description;
        return this;
    }

    public RcsMessage media(String media) {
        this.media = media;
        return this;
    }

    public RcsMessage addVariable(String key, String value) {
        variable.put(key, value);
        return this;
    }

    public void setMessage(JsonObject message) {
        if (title != null) message.addProperty("title", title);
        if (description != null) message.addProperty("description", description);
        if (media != null) message.addProperty("media", media);
        if (!variable.isEmpty()) variable.forEach(message::addProperty);
    }

    public void setMessage(JsonObject message, int count) {
        if (title != null) message.addProperty("title" + count, title);
        if (description != null) message.addProperty("description" + count, description);
        if (media != null) message.addProperty("media" + count, media);
    }
}
