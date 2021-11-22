package main.java.bizapiLib.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper=true)
public class TokenResponse extends BizapiResponse {
    String accesstoken;
    String type;
    String expired;
}
