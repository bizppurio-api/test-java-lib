package main.java.bizapiLib.bizFacotry.message;

import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.action.resend.RCSResend;
import main.java.bizapiLib.action.send.RCSSend;
import main.java.bizapiLib.common.RcsMessage;

public class RCSFactory extends MessageFactory {

    public RCSFactory(TokenIssuer tokenIssuer) {
        super(tokenIssuer);
    }

    public RCSSend sendAction() {
        return new RCSSend(tokenIssuer);
    }

    public RCSSend sendAction(String refkey, String to, String from, RcsMessage rcsMessage, String messagebaseid, String chatbotid, String header) {
        return new RCSSend(tokenIssuer)
                .refeky(refkey)
                .to(to)
                .from(from)
                .message(rcsMessage)
                .messagebaseid(messagebaseid)
                .chatbotid(chatbotid)
                .header(header);
    }

    public RCSResend createResend() {
        return new RCSResend();
    }

    public RCSResend createResend(String messagebaseid, String chatbotid, String header, RcsMessage... message) {
        return new RCSResend().messagebaseid(messagebaseid).chatbotid(chatbotid).header(header).message(message);
    }

    public RcsMessage createMessage() {
        return new RcsMessage();
    }

    public RcsMessage createMessage(String description) {
        return new RcsMessage().description(description);
    }
}
