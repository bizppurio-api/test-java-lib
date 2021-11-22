package main.java.bizapiLib.action.send;

import com.google.gson.JsonObject;
import main.java.bizapiLib.action.BizAction;
import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.response.FileResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MMSSendFile extends BizAction<FileResponse> {
    File file;

    public MMSSendFile(TokenIssuer tokenIssuer) {
        super(tokenIssuer);
    }

    public MMSSendFile file(File file) {
        this.file = file;
        return this;
    }

    @Override
    protected JsonObject validation() {
        return null;
    }

    @Override
    protected FileResponse createResponse(JsonObject o) {
        return gson.fromJson(o, FileResponse.class);
    }

    public FileResponse execute() throws Exception {
        String boundary = UUID.randomUUID().toString();
        Map<String, String> requestProperty = new HashMap<>();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        requestProperty.put("Content-Type", "multipart/form-data;charset=utf-8;boundary=" + boundary);
        String requestBody = "";
        requestBody += "--" + boundary + "\r\n";
        requestBody += "Content-Disposition: form-data; name=\"account\"" + "\r\n";
        requestBody += "Content-Type: text/plain; charset=UTF-8" + "\r\n";
        requestBody += "Content-Transfer-Encoding: 8bit" + "\r\n";
        requestBody += "\r\n";
        requestBody += tokenIssuer.getAccount() + "\r\n";
        requestBody += "--" + boundary + "\r\n";
        requestBody += "Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"" + "\r\n";
        requestBody += "Content-Type: image/jpeg" + "\r\n";
        requestBody += "Content-Transfer-Encoding: binary" + "\r\n";
        requestBody += "\r\n";

        byteArrayOutputStream.write(requestBody.getBytes(StandardCharsets.UTF_8));

        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] buffer = new byte[(int) file.length()];
            int read;
            while ((read = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, read);
            }
        }

        String endBoundary = "\r\n--" + boundary + "--\r\n";
        byteArrayOutputStream.write(endBoundary.getBytes());
        byteArrayOutputStream.flush();

        return execute("v1/file", requestProperty, byteArrayOutputStream.toByteArray());
    }

}
