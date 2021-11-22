# bizapi-java-lib

## gradle

```java
dependencies {
	implementation 'com.github.jitpack:example:1.1'
}
```

## Usage

For additional examples, refer to the examples folder.

```java
TokenIssuer tokenIssuer = new TokenIssuer("account", "password");

// staging server setting (Testing)
// Please remove the code when you send it to the operation server.
tokenIssuer.certificateIgnored();
tokenIssuer.setDomain(BizapiDefine.BIZAPI_STAGING_DOMAIN);

// Tokens must be issued.
// Tokens are maintained 24 hours a day.
TokenResponse tokenResponse = tokenIssuer.execute();

sms = new SMSFactory(tokenIssuer);

// required parameter (refkey, to, from, message)
SMSSend smsSend = sms.sendAction("refkey", "01012341234", "01012341234","The maximum size of an sms message is 90 bytes.");
MessageResponse response = (MessageResponse) smsSend.execute();

// builder pattern
SMSSend smsSend = sms.sendAction()
    .refeky("refkey")
    .to("01012341234")
    .from("01012341234")
    .message("The maximum size of an sms message is 90 bytes.");
MessageResponse response = (MessageResponse) smsSend.execute();
```

