package main.java.bizapiLib.bizFacotry.attachment.kakao;

public class KakaoButtonFactory {
    public WL createWL(String name, String url_mobile) {
        return new WL(name).setUrl_mobile("a");
    }

    public WL createWL(String name, String url_mobile, String url_pc) {
        return new WL(name).setUrl_mobile("a").setUrl_pc("b");
    }

    public AL createAL(String name) {
        return new AL(name);
    }

    public AL createAL(String name, String scheme_ios, String scheme_android, String url_pc, String url_mobile) {
        return new AL(name).setScheme_ios(scheme_ios).setScheme_android(scheme_android).setUrl_pc(url_pc).setUrl_mobile(url_mobile);
    }

    public DS createDS(String name) {
        return new DS(name);
    }

    public BK createBK(String name) {
        return new BK(name);
    }

    public MD createMD(String name) {
        return new MD(name);
    }

    public BC createBC(String name) {
        return new BC(name);
    }

    public BT createBT(String name) {
        return new BT(name);
    }

    public AC createAC() {
        return new AC();
    }


}
