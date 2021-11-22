package main.java.bizapiLib.common;

import com.google.gson.Gson;
import main.java.bizapiLib.response.BizapiResponse;

public class BizapiDefine {
    public static final String BIZAPI_PRODUCTION_DOMAIN = "https://api.bizppurio.com/";
    public static final String BIZAPI_STAGING_DOMAIN = "https://dev-api.bizppurio.com/";

    public static final Gson gson = new Gson();
    public static final BizapiResponse INVALID_TOKEN = BizapiResponse.builder().code("3002").description("jwt must be provided.").build();
    public static final BizapiResponse INVALID_MESSAGE = BizapiResponse.builder().code("2000").description("invalid message.").build();
    public static final BizapiResponse BIZPPURIO_NOT_VALID_USER = BizapiResponse.builder().code("3006").description("invalid account in bizppurio.").build();
    public static final BizapiResponse BIZPPURIO_NOT_VALID_PASSWORD = BizapiResponse.builder().code("3007").description("invalid password in bizppurio.").build();
}
