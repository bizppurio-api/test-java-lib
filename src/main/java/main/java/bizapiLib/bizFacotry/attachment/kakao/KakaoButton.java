package main.java.bizapiLib.bizFacotry.attachment.kakao;

import com.google.gson.JsonObject;
import lombok.Getter;

public class KakaoButton {
    @Getter
    JsonObject button = new JsonObject();

    public KakaoButton (String name, String type) {
        button.addProperty("name", name);
        button.addProperty("type", type);
    }

    @Override
    public String toString() {
        return button.toString();
    }
}
