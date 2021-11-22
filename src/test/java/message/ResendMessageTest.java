package message;

import main.java.bizapiLib.action.BizAction;
import main.java.bizapiLib.action.TokenIssuer;
import main.java.bizapiLib.action.resend.LMSResend;
import main.java.bizapiLib.action.resend.MMSResend;
import main.java.bizapiLib.action.resend.RCSResend;
import main.java.bizapiLib.action.resend.SMSResend;
import main.java.bizapiLib.action.send.ATSend;
import main.java.bizapiLib.bizFacotry.attachment.kakao.KakaoButtonFactory;
import main.java.bizapiLib.bizFacotry.attachment.rcs.RcsButtonFactory;
import main.java.bizapiLib.bizFacotry.message.*;
import main.java.bizapiLib.common.BizapiDefine;
import main.java.bizapiLib.common.RcsMessage;
import main.java.bizapiLib.response.FileResponse;
import main.java.bizapiLib.response.MessageResponse;
import main.java.bizapiLib.response.TokenResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ResendMessageTest {
    private static TokenIssuer tokenIssuer;
    private static SMSFactory sms;
    private static LMSFactory lms;
    private static MMSFactory mms;
    private static ATFactory at;
    private static FTFactory ft;
    private static RCSFactory rcs;
    private static KakaoButtonFactory kakaoButtonFactory;
    private static RcsButtonFactory rcsButtonFactory;

    @BeforeClass
    public static void setToken() throws Exception {
        tokenIssuer = new TokenIssuer("account", "password");
        tokenIssuer.certificateIgnored();
        tokenIssuer.setDomain(BizapiDefine.BIZAPI_STAGING_DOMAIN);

        TokenResponse t = tokenIssuer.execute();
        sms = new SMSFactory(tokenIssuer);
        lms = new LMSFactory(tokenIssuer);
        mms = new MMSFactory(tokenIssuer);
        at = new ATFactory(tokenIssuer);
        ft = new FTFactory(tokenIssuer);
        rcs = new RCSFactory(tokenIssuer);
        kakaoButtonFactory = new KakaoButtonFactory();
        rcsButtonFactory = new RcsButtonFactory();
    }

    public void execute(BizAction action) throws Exception {
        MessageResponse response = (MessageResponse) action.execute();
        try {
            assertEquals(200, response.getResponseCode());
        } catch (AssertionError e) {
            System.out.println(response);
            throw e;
        }
    }

    @Test
    public void kakaoAndsmsTest() throws Exception {
        ATSend atSend = at.sendAction().refeky("refkey").to("01012341234").from("01012341234").message("The maximum size of an at message is 1000 length.").senderkey("senderkey").templatecode("templatecode");
        SMSResend smsResend = sms.createResend("The maximum size of an sms message is 90 bytes.");

        atSend.resendFirst(smsResend);
        execute(atSend);
    }

    @Test
    public void kakaoAndlmsTest() throws Exception {
        ATSend atSend = at.sendAction().refeky("refkey").to("01012341234").from("01012341234").message("The maximum size of an at message is 1000 length.").senderkey("senderkey").templatecode("templatecode");
        LMSResend lmsResend = lms.createResend("The maximum size of an sms message is 90 bytes.");

        atSend.resendFirst(lmsResend);
        execute(atSend);
    }

    @Test
    public void kakaoAndmmsTest() throws Exception {
        ATSend atSend = at.sendAction().refeky("refkey").to("01012341234").from("01012341234").message("The maximum size of an at message is 1000 length.").senderkey("senderkey").templatecode("templatecode");
        FileResponse response = mms.sendFileAction(new File("D:/bg.jpg")).execute();

        MMSResend mmsResend = mms.createResend("The maximum size of an mms message is 90 bytes.", response.getFilekey());
        atSend.resendFirst(mmsResend);
        System.out.println(atSend);
        execute(atSend);
    }

    @Test
    public void kakaoAndRcsTest() throws Exception {
        ATSend atSend = at.sendAction().refeky("refkey").to("01012341234").from("01012341234").message("The maximum size of an at message is 1000 length.").senderkey("senderkey").templatecode("templatecode");
        RcsMessage rcsMessage = rcs.createMessage("rcs message.");
        RCSResend rcsResend = rcs.createResend("SS000000", "12341234","1", rcsMessage);

        atSend.resendFirst(rcsResend);
        execute(atSend);
    }

    @Test
    public void kakaoAndRcsAndSmsTest() throws Exception {
        ATSend atSend = at.sendAction().refeky("refkey").to("01012341234").from("01012341234").message("The maximum size of an at message is 1000 length.").senderkey("senderkey").templatecode("templatecode");
        SMSResend smsResend = sms.createResend("The maximum size of an sms message is 90 bytes.");
        RcsMessage rcsMessage = rcs.createMessage("rcs message.");
        RCSResend rcsResend = rcs.createResend("SS000000", "12341234","0", rcsMessage);

        atSend.resendFirst(rcsResend);
        atSend.resendSecond(smsResend);
        execute(atSend);
    }
}
