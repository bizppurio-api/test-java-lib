package main.java.bizapiLib.bizFacotry.attachment.kakao;


public class BT extends KakaoButton {
    public BT(String name) {
        super(name, "BT");
    }

    public BT setChat_extra(String chat_extra) {
        button.addProperty("chat_extra", chat_extra);
        return this;
    }

    public BT setChat_event(String chat_event) {
        button.addProperty("chat_event", chat_event);
        return this;
    }
}
