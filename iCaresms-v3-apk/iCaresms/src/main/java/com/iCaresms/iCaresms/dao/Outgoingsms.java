package com.iCaresms.iCaresms.dao;

import com.iCaresms.iCaresms.Constants.SMSConstants;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "outgoing_sms")
public class Outgoingsms implements JSONConverter{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String recipient;

    protected String message;
    protected String status;//sent,failed, queued

    public Outgoingsms() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public Map<String, Object> toMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("event", SMSConstants.EVENT_SEND);

        Map<String, Object> messageObject = new HashMap<>();
        messageObject.put("id", String.valueOf(id));
        messageObject.put("to", recipient);
        messageObject.put("message", message);

        map.put("messages", messageObject);

        return map;
    }

}
