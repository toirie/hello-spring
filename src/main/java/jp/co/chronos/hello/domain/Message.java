package jp.co.chronos.hello.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="MESSAGE")
public class Message {
    @EmbeddedId
    private MessagePrimaryKey messagePrimaryKey;
    @Column(name="MESSAGE")
    private String message;
    public Message() {

    }
    public Message(MessagePrimaryKey messagePrimaryKey, String message) {
        this.messagePrimaryKey = messagePrimaryKey;
        this.message = message;
    }
    public void setMessagePrimaryKey(MessagePrimaryKey messagePrimaryKey) {
        this.messagePrimaryKey = messagePrimaryKey;
    }
    public MessagePrimaryKey getMessagePrimaryKey() {
        return this.messagePrimaryKey;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }
}
