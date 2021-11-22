package main.java.bizapiLib.action;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import main.java.bizapiLib.common.BizapiDefine;
import main.java.bizapiLib.common.BizapiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public abstract class BizAction<TResponse> {
    protected Gson gson = BizapiDefine.gson;
    protected TokenIssuer tokenIssuer;
    protected JsonObject requestBody = new JsonObject();

    public BizAction() {}

    public BizAction(TokenIssuer tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }

    abstract protected JsonObject validation();

    protected TResponse execute(String endPoint, Map<String, String> requestProperty, byte[] body) throws Exception {
        JsonObject response = validation();
        if (response != null) {
            response.addProperty("responseData", response.toString());
            return createResponse(response);
        }

        URL url = new URL(tokenIssuer.getDomain() + endPoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try(AutoCloseable ignored = connection::disconnect) {
            connection.setRequestProperty("User-Agent", "bizapi-lib/java " + System.getProperty("os.name"));
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(15000);

            if (requestProperty != null) {
                requestProperty.forEach(connection::setRequestProperty);
            }

            if (body != null) {
                connection.getOutputStream().write(body);
                connection.getOutputStream().flush();
                connection.getOutputStream().close();
            }

            return createResponse(connection);
        }
    }

    private TResponse createResponse(HttpURLConnection connection) throws IOException, BizapiException {
        String input;
        StringBuilder result = new StringBuilder();
        try (InputStream inputStream = (connection.getResponseCode() == 200) ? connection.getInputStream() : connection.getErrorStream()) {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            while ((input = in.readLine()) != null) {
                result.append(input);
            }
        }

        return createResponse(connection.getResponseCode(), connection.getResponseMessage(), result.toString());
    }

    protected TResponse createResponse(int responseCode, String responseMessage, String responseData) throws BizapiException {
        try {
            JsonObject o = gson.fromJson(responseData, JsonObject.class);
            o.addProperty("responseCode", responseCode);
            o.addProperty("responseMessage", responseMessage);
            o.addProperty("responseData", responseData);

            return createResponse(o);
        } catch (JsonSyntaxException e) {
            throw new BizapiException("JsonSyntaxException.\r\n"
                    + "responseCode : " + responseCode + "\r\n"
                    + "responseMessage : " + responseMessage + "\r\n"
                    + "responseData : " + responseData + "\r\n");
        }
    }

    protected abstract TResponse createResponse(JsonObject o);

    public TResponse execute() throws Exception {
        Map<String, String> requestProperty = new HashMap<>();
        requestProperty.put("Content-Type", "application/json");
        requestProperty.put("Charset", "UTF-8");
        requestProperty.put("Authorization", "Bearer " + tokenIssuer.getToken());

        return execute("v3/message", requestProperty, requestBody.toString().getBytes());
    }

    @Override
    public String toString() {
        return requestBody.toString();
    }
}
