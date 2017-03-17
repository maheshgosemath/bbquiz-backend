package in.theuniquemedia.brainbout.common.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 3/16/17.
 */
public class EmailVO implements Serializable{
    private String sender;
    private String receiver;
    private String message;
    private String fromLabel;
    private String toLabel;
    private String subject;

    public EmailVO(String sender, String receiver, String message, String fromLabel, String toLabel, String subject) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.fromLabel = fromLabel;
        this.toLabel = toLabel;
        this.subject = subject;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromLabel() {
        return fromLabel;
    }

    public void setFromLabel(String fromLabel) {
        this.fromLabel = fromLabel;
    }

    public String getToLabel() {
        return toLabel;
    }

    public void setToLabel(String toLabel) {
        this.toLabel = toLabel;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
