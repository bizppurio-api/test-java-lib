package main.java.bizapiLib.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BizapiResponse {
    int responseCode;
    String responseMessage;
    String responseData;
    String code;
    String description;
}
