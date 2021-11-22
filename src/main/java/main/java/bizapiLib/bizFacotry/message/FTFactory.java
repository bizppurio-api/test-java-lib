package main.java.bizapiLib.bizFacotry.message;

import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.action.resend.FTResend;
import main.java.bizapiLib.action.send.FTSend;

public class FTFactory extends MessageFactory {

    public FTFactory(TokenIssuer tokenIssuer) {
        super(tokenIssuer);
    }

    public FTSend sendAction() {
        return new FTSend(tokenIssuer);
    }

    public FTSend sendAction(String refkey, String to, String from, String message, String senderkey) {
        return new FTSend(tokenIssuer)
                .refeky(refkey)
                .to(to)
                .from(from)
                .message(message)
                .senderkey(senderkey);
    }

    public FTResend getResend() {
        return new FTResend();
    }

    public FTResend getResend(String message, String senderkey) {
        return new FTResend().message(message).senderkey(senderkey);
    }
}
