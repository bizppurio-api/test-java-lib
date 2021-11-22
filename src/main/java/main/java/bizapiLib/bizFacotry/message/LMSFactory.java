package main.java.bizapiLib.bizFacotry.message;


import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.action.resend.LMSResend;
import main.java.bizapiLib.action.send.LMSSend;

public class LMSFactory extends MessageFactory {

    public LMSFactory(TokenIssuer tokenIssuer) {
        super(tokenIssuer);
    }

    public LMSSend sendAction() {
        return new LMSSend(tokenIssuer);
    }

    public LMSSend sendAction(String refkey, String to, String from, String message) {
        return new LMSSend(tokenIssuer)
                .refeky(refkey)
                .to(to)
                .from(from)
                .message(message);
    }

    public LMSResend createResend() {
        return new LMSResend();
    }

    public LMSResend createResend(String message) {
        return new LMSResend().message(message);
    }
}
