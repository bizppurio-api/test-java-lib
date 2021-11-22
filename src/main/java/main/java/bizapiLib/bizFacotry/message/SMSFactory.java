package main.java.bizapiLib.bizFacotry.message;

import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.action.resend.SMSResend;
import main.java.bizapiLib.action.send.SMSSend;

public class SMSFactory extends MessageFactory {

    public SMSFactory(TokenIssuer tokenIssuer) {
        super(tokenIssuer);
    }

    public SMSSend sendAction() {
        return new SMSSend(tokenIssuer);
    }

    public SMSSend sendAction(String refkey, String to, String from, String message) {
        return new SMSSend(tokenIssuer)
                .refeky(refkey)
                .to(to)
                .from(from)
                .message(message);
    }

    public SMSResend createResend() {
        return new SMSResend();
    }

    public SMSResend createResend(String message) {
        return new SMSResend().message(message);
    }
}
