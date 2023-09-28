package com.iCaresms.iCaresms.EnvayaSMS;

import jakarta.persistence.*;

@Entity
@Table(name = "outgoing_sms")
public class OutgoinngSMS{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String recipient;

    protected String message;
    protected String status;//sent,failed, queued

    public OutgoinngSMS() {
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

}