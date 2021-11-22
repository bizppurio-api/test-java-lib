package main.java.bizapiLib.bizFacotry.attachment.kakao;


public class WL extends KakaoButton {
    public WL(String name) {
        super(name, "WL");
    }

    public WL setUrl_mobile(String url_mobile) {
        button.addProperty("url_mobile", url_mobile);
        return this;
    }

    public WL setUrl_pc(String url_pc) {
        button.addProperty("url_pc", url_pc);
        return this;
    }
}
