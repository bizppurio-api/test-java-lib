/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package main.java.bizapiLib.bizFacotry.message;

import lombok.Getter;
import main.java.bizapiLib.action.TokenIssuer;

class MessageFactory {
    @Getter
    TokenIssuer tokenIssuer;

    public MessageFactory(TokenIssuer tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }
}
