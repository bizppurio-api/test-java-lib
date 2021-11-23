# bizapi-java-lib

## gradle (jitpack)

```java
repositories {
	maven { url 'https://jitpack.io' }
}
dependencies {
	implementation 'com.github.bizppurio-api:test-javascript-lib:1.0.0'
}
```

## gradle (maven Central)

```java
repositories {
	mavenCentral()
}
dependencies {
	implementation 'io.github.bizppurio-api:test-javascript-lib:1.0.0'
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

