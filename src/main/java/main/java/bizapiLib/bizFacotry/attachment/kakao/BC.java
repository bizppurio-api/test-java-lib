package main.java.bizapiLib.bizFacotry.attachment.kakao;


public class BC extends KakaoButton {
    public BC(String name) {
        super(name, "BC");
    }

    public BC setChat_extra(String chat_extra) {
        button.addProperty("chat_extra", chat_extra);
        return this;
    }
}
