package main.java.bizapiLib.bizFacotry.message;

import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.action.resend.ATResend;
import main.java.bizapiLib.action.send.ATSend;

public class ATFactory extends MessageFactory {

    public ATFactory(TokenIssuer tokenIssuer) {
        super(tokenIssuer);
    }

    public ATSend sendAction() {
        return new ATSend(tokenIssuer);
    }

    public ATSend sendAction(String refkey, String to, String from, String message, String senderkey, String templatecode) {
        return new ATSend(tokenIssuer)
                .refeky(refkey)
                .to(to)
                .from(from)
                .message(message)
                .senderkey(senderkey)
                .templatecode(templatecode);
    }

    public ATResend getResend() {
        return new ATResend();
    }

    public ATResend getResend(String message, String senderkey, String templatecode) {
        return new ATResend()
                .message(message)
                .senderkey(senderkey)
                .templatecode(templatecode);
    }
}
