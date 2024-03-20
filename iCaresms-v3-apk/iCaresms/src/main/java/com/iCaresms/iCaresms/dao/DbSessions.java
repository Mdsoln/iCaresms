package com.iCaresms.iCaresms.dao;

import org.hibernate.query.Query;

import java.util.List;

// This is a class that tries to hibernate the sql interacting with database
//its just needs a lot of abstract class to define the session's queries
public class DbSessions {

    public void saveReceivedMessage(String sender, String messageContent, String messageType) {
        DbSession session = getSession();
        String queryStr = "INSERT INTO incomingsms (sender, messageContent, messageType) VALUES (?, ?, ?)";
        Query query = session.createQuery(queryStr);
        query.setParameter(1, sender);
        query.setParameter(2, messageContent);
        query.setParameter(3, messageType);
        query.executeUpdate();

    }

    //polling outgoing sms from db
    public List<Outgoingsms> findByStatus(String status){
        DbSession session = getSession();
        String sqlString = "SELECT id, recipient, message, status FROM outgoing_sms WHERE status = :status ";
        Query query = session.createQuery(sqlString);
        query.setParameter("status", status);
        return query.list();
    }

    //updating status of polled sms
    public void updateStatusOutgoingsms(Outgoingsms outgoingsms){
        DbSession session = getSession();
        String sqlString = "INSERT INTO outgoing_sms (id, recipient, message, status) VALUES(?,?,?,?) ";
        Query query = session.createQuery(sqlString);
        query.setParameter(1, outgoingsms.getId());
        query.setParameter(2, outgoingsms.getRecipient());
        query.setParameter(3, outgoingsms.getMessage());
        query.setParameter(4, outgoingsms.getStatus());

        query.executeUpdate();
    }
}
