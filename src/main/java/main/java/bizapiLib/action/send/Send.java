package main.java.bizapiLib.action.send;

import com.google.common.base.Strings;
import com.google.gson.JsonObject;
import main.java.bizapiLib.action.BizAction;
import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.action.resend.Resend;
import main.java.bizapiLib.common.BizapiDefine;
import main.java.bizapiLib.common.BizapiUtil;
import main.java.bizapiLib.response.MessageResponse;

import java.lang.reflect.Type;
import java.util.*;

abstract class Send<T, TResponse> extends BizAction<TResponse> {
    JsonObject content = new JsonObject();
    JsonObject contentDetail = new JsonObject();
    JsonObject recontent;
    JsonObject resend;
    Resend first;
    Resend second;

    static final List<String> commonRequiredField = Arrays.asList("to", "from", "refkey");
    static final Map<String, Integer> commonMaxField = new HashMap<String, Integer>() {
        {
            put("to", 16);
            put("from", 16);
            put("country", 3);
            put("refkey", 32);
            put("userinfo", 50);
        }
    };

    protected List<String> requiredFieldByType = new ArrayList<>();
    protected Map<String, Integer> maxFiledByType = new HashMap<>();

    public Send(TokenIssuer tokenIssuer, String type) {
        super(tokenIssuer);
        requestBody.addProperty("account", tokenIssuer.getAccount());
        requestBody.addProperty("type", type);
        requestBody.add("content", content);
    }

    public T from(String from) {
        requestBody.addProperty("from", from);
        return (T) this;
    }

    public T to(String to) {
        requestBody.addProperty("to", to);
        return (T) this;
    }

    public T refeky(String refkey) {
        requestBody.addProperty("refkey", refkey);
        return (T) this;
    }

    public T country(String country) {
        requestBody.addProperty("country", country);
        return (T) this;
    }

    public T userinfo(String userinfo) {
        requestBody.addProperty("userinfo", userinfo);
        return (T) this;
    }

    public T setRequestBody(String requestBody) {
        this.requestBody = BizapiDefine.gson.fromJson(requestBody, JsonObject.class);
        return (T) this;
    }

    void resend() {
        if (recontent == null || resend == null) {
            recontent = new JsonObject();
            resend = new JsonObject();
            requestBody.add("resend", resend);
            requestBody.add("recontent", recontent);
        }
    }

    T resendFirst(Resend first) {
        resend();
        resend.addProperty("first", first.getType());
        recontent.add(first.getType(), first.getRecontentDetail());
        this.first = first;
        return (T) this;
    }

    T resendSecond(Resend second) {
        resend();
        resend.addProperty("second", second.getType());
        recontent.add(second.getType(), second.getRecontentDetail());
        this.second = second;
        return (T) this;
    }

    @Override
    protected TResponse createResponse(JsonObject o) {
        return gson.fromJson(o, (Type) MessageResponse.class);
    }

    protected JsonObject validation() {
        if (Strings.isNullOrEmpty(tokenIssuer.getToken())) {
            return BizapiUtil.makeResponse(BizapiDefine.INVALID_TOKEN.getCode(), BizapiDefine.INVALID_TOKEN.getDescription(), null);
        }

        JsonObject o;
        o = validation(commonRequiredField, commonMaxField);
        if (o != null) return o;

        return validation(requiredFieldByType, maxFiledByType);
    }

    protected JsonObject validation(List<String> requiredField, Map<String, Integer> maxfield) {
        return BizapiUtil.validation(requestBody, requiredField, maxfield);
    }

    JsonObject resendValidation(JsonObject requestBody) {
        JsonObject response = null;
        if (first != null) {
            response = first.validation(requestBody);
            if (response != null) return response;
        }

        if (second != null) {
            response = second.validation(requestBody);
            if (response != null) return response;
        }

        return response;
    }




}
