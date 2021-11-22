package main.java.bizapiLib.common;

import com.google.common.base.Strings;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;

public class BizapiUtil {
    public static JsonObject required(JsonObject jsonObject, String key, JsonElement refkey) {
        JsonElement jsonElement = divideKey(jsonObject, key);

        if (jsonElement == null
                || (jsonElement.isJsonPrimitive() && Strings.isNullOrEmpty(jsonElement.getAsString()))
                || (jsonElement.isJsonObject() && "{}".equals(jsonElement.getAsJsonObject().toString()))) {
            return makeResponse(BizapiDefine.INVALID_MESSAGE.getCode(), "The " + key + " field is required.", refkey);
        }
        return null;
    }

    @SneakyThrows
    public static JsonObject maxByte(JsonObject jsonObject, String key, int max, JsonElement refkey) {
        JsonElement jsonElement = divideKey(jsonObject, key);

        if (jsonElement != null && jsonElement.isJsonPrimitive() && jsonElement.getAsString().getBytes("EUC-KR").length > max) {
            return makeResponse(BizapiDefine.INVALID_MESSAGE.getCode(), "The " + key + " may not be greater than " + max + " characters.", refkey);
        }
        return null;
    }

    public static JsonElement divideKey(JsonObject requestBody, String key) {
        String[] keys = key.split("\\.");

        JsonElement jsonElement = null;
        JsonObject o = requestBody;
        for (String k : keys) {
            jsonElement = o.get(k);
            if (jsonElement == null || !jsonElement.isJsonObject()) break;
            o = jsonElement.getAsJsonObject();
        }

        return jsonElement;
    }

    @SneakyThrows
    public static JsonObject maxLength(JsonObject jsonObject, String key, int max, JsonElement refkey) {
        JsonElement jsonElement = divideKey(jsonObject, key);
        if (jsonElement != null && jsonElement.getAsString().length() > max) {
            return makeResponse(BizapiDefine.INVALID_MESSAGE.getCode(), "The " + key + " may not be greater than " + max + " length.", refkey);
        }
        return null;
    }

    public static JsonObject makeResponse(String code, String description, JsonElement refkey) {
        JsonObject response = new JsonObject();
        response.addProperty("code", code);
        response.addProperty("description", description);
        if (refkey != null && !Strings.isNullOrEmpty(refkey.getAsString())) {
            response.add("refkey", refkey);
        }
        return response;
    }

    public static JsonObject validation(JsonObject requestBody, List<String> requiredFieldByType, Map<String, Integer> maxFiledByType) {
        JsonObject o;
        for (String field : requiredFieldByType) {
            o = BizapiUtil.required(requestBody, field, requestBody.get("refkey"));
            if (o != null) return o;
        }

        for (String key : maxFiledByType.keySet()) {
            o = BizapiUtil.maxByte(requestBody, key, maxFiledByType.get(key), requestBody.get("refkey"));
            if (o != null) return o;
        }
        return null;
    }
}
