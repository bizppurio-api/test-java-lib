package main.java.bizapiLib.action;

import com.google.common.base.Strings;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import main.java.bizapiLib.common.BizapiDefine;
import main.java.bizapiLib.response.TokenResponse;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.Map;

public class TokenIssuer extends BizAction<TokenResponse> {
    @Getter
    private final String account;
    private final String pw;
    @Getter @Setter
    private String token;
    @Getter @Setter
    String domain = BizapiDefine.BIZAPI_STAGING_DOMAIN;

    public TokenIssuer(String account, String pw){
        tokenIssuer = this;
        this.account = account;
        this.pw = pw;
    }

    public void certificateIgnored() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() { return null; }
            public void checkClientTrusted(X509Certificate[] chain, String authType) { }
            public void checkServerTrusted(X509Certificate[] chain, String authType) { } } };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = (hostname, session) -> true;
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

    @Override
    public TokenResponse execute() throws Exception {
        Map<String, String> requestProperty = new HashMap<>();
        Encoder encoder = Base64.getEncoder();
        requestProperty.put("Authorization", "Basic " +  encoder.encodeToString((account + ":" + pw).getBytes()));

        TokenResponse tokenResponse = execute("v1/token", requestProperty, null);
        token = tokenResponse.getAccesstoken();

        return tokenResponse;
    }

    @Override
    protected JsonObject validation() {
        if (Strings.isNullOrEmpty(account)) {
            return null;
        }

        if (Strings.isNullOrEmpty(pw)) {
            return null;
        }

        return null;
    }

    @Override
    protected TokenResponse createResponse(JsonObject o) {
        return gson.fromJson(o, TokenResponse.class);
    }
}
