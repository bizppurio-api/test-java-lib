package main.java.bizapiLib.bizFacotry.attachment.kakao;

public class AL extends KakaoButton {
    public AL(String name) {
        super(name, "AL");
    }

    public AL setUrl_mobile(String url_mobile) {
        button.addProperty("url_mobile", url_mobile);
        return this;
    }

    public AL setUrl_pc(String url_pc) {
        button.addProperty("url_pc", url_pc);
        return this;
    }

    public AL setScheme_ios(String scheme_ios) {
        button.addProperty("scheme_ios", scheme_ios);
        return this;
    }

    public AL setScheme_android(String scheme_android) {
        button.addProperty("url_pc", scheme_android);
        return this;
    }
}
