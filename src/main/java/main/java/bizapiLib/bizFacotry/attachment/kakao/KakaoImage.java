package main.java.bizapiLib.bizFacotry.attachment.kakao;

import com.google.gson.JsonObject;
import lombok.Getter;

public class KakaoImage {
    @Getter
    JsonObject image = new JsonObject();

    public KakaoImage img_url(String img_url) {
        image.addProperty("img_url", img_url);
        return this;
    }

    public KakaoImage img_link(String img_link) {
        image.addProperty("img_link", img_link);
        return this;
    }
}
