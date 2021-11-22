package main.java.bizapiLib.action.resend;

import com.google.gson.JsonObject;
import lombok.Getter;
import main.java.bizapiLib.common.BizapiUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Resend {
    @Getter
    JsonObject recontentDetail = new JsonObject();
    @Getter
    String type;

    protected List<String> requiredFieldByType = new ArrayList<>();
    protected Map<String, Integer> maxFiledByType = new HashMap<>();

    public JsonObject validation(JsonObject requestBody) {
        return BizapiUtil.validation(requestBody, requiredFieldByType, maxFiledByType);
    }
}
