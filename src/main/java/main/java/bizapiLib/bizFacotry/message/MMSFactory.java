package main.java.bizapiLib.bizFacotry.message;

import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.action.resend.MMSResend;
import main.java.bizapiLib.action.send.MMSSend;
import main.java.bizapiLib.action.send.MMSSendFile;

import java.io.File;

public class MMSFactory extends MessageFactory {

    public MMSFactory(TokenIssuer tokenIssuer) {
        super(tokenIssuer);
    }

    public MMSSend sendAction() {
        return new MMSSend(tokenIssuer);
    }

    public MMSSend sendAction(String refkey, String to, String from, String message, String... filekeys) {
        return new MMSSend(tokenIssuer)
                .refeky(refkey)
                .to(to)
                .from(from)
                .message(message)
                .filekey(filekeys);
    }

    public MMSSendFile sendFileAction() {
        return new MMSSendFile(tokenIssuer);
    }

    public MMSSendFile sendFileAction(File file) {
        return new MMSSendFile(tokenIssuer).file(file);
    }

    public MMSResend createResend() {
        return new MMSResend();
    }

    public MMSResend createResend(String message, String... filekeys) {
        return new MMSResend().message(message).filekey(filekeys);
    }
}
