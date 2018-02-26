package jp.co.chronos.hello.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MessagePrimaryKey implements Serializable {
    @Column(name="MESSAGE_ID")
    private String messageId;
    public MessagePrimaryKey() {

    }
    public MessagePrimaryKey(String messageId) {
        this.messageId = messageId;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    public String getMessageId() {
        return this.messageId;
    }
}
