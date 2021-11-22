package main.java.bizapiLib.action.send;

import com.google.common.base.Strings;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.common.BizapiDefine;
import main.java.bizapiLib.common.BizapiUtil;
import main.java.bizapiLib.response.MessageResponse;

public class MMSSend extends Send<MMSSend, MessageResponse> {
    public MMSSend(TokenIssuer tokenIssuer) {
        super(tokenIssuer, "mms");
        content.add("mms", contentDetail);
    }

    public MMSSend message(String message) {
        contentDetail.addProperty("message", message);
        return this;
    }

    public MMSSend setSubject(String subject) {
        contentDetail.addProperty("subject", subject);
        return this;
    }

    public MMSSend filekey(String... filekeys) {
        JsonArray file = new JsonArray();
        for (String filekey : filekeys) {
            JsonObject o = new JsonObject();
            o.addProperty("type", getFileType(filekey));
            o.addProperty("key", filekey);
            file.add(o);
        }
        contentDetail.add("file", file);
        return this;
    }

    @Override
    protected JsonObject validation() {
        requiredFieldByType.add("content.mms.message");
        maxFiledByType.put("content.mms.message", 2000);
        maxFiledByType.put("content.mms.subject", 64);

        JsonArray file = contentDetail.getAsJsonArray("file");

        if (file == null || file.size() == 0) {
            return BizapiUtil.makeResponse(BizapiDefine.INVALID_MESSAGE.getCode(), "file[] is required", requestBody.get("refkey"));
        }

        if (file.size() > 3) {
            return BizapiUtil.makeResponse(BizapiDefine.INVALID_MESSAGE.getCode(), "file[] maximum size is 3 (size:" + file.size() + ")", requestBody.get("refkey"));
        }

        JsonObject o;
        for (int i = 0; i < file.size(); i++) {
            o = file.get(i).getAsJsonObject();
            if (o.get("type").isJsonNull() || Strings.isNullOrEmpty(o.get("type").getAsString())) {
                return BizapiUtil.makeResponse(BizapiDefine.INVALID_MESSAGE.getCode(), "file type is not empty", requestBody.get("refkey"));
            }

            if (o.get("key").isJsonNull() || Strings.isNullOrEmpty(o.get("key").getAsString())) {
                return BizapiUtil.makeResponse(BizapiDefine.INVALID_MESSAGE.getCode(), "file key is not empty", requestBody.get("refkey"));
            }
        }

        return super.validation();
    }

    private String getFileType(String filekey) {
        String type = null;

        if (!Strings.isNullOrEmpty(filekey)) {
            String extension = filekey.substring(filekey.lastIndexOf(".") + 1).toUpperCase();
            switch (extension) {
                case "JPG":
                case "JPEG":
                    type = "IMG";
                    break;
            }
        }

        return type;
    }
}
