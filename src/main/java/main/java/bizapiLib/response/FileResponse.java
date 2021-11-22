package main.java.bizapiLib.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper=true)
public class FileResponse extends BizapiResponse{
    String filekey;
}
