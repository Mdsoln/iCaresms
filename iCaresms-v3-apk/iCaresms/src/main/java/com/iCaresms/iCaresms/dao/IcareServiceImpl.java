package com.iCaresms.iCaresms.dao;

import com.iCaresms.iCaresms.Constants.SMSConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IcareServiceImpl implements IcareService{
    @Autowired
    DbSessions dao;
    @Override
    public void processIncomingAction(String from, String message, String type){
        dao.saveReceivedMessage(from,message,type);
    }

    @Override
    public List<Map<String, Object>> handleOutgoingSms() throws Exception {
        List<Outgoingsms> outgoingSMSList = dao.findByStatus("queued");

        List<Map<String, Object>> eventsList = new ArrayList<>();
        for (Outgoingsms outgoingSMS : outgoingSMSList) {
            eventsList.add(outgoingSMS.toMap());
            outgoingSMS.setStatus(SMSConstants.STATUS_SENT);
            dao.updateStatusOutgoingsms(outgoingSMS);
        }
        return eventsList;
    }

    @Override
    public Map<String, Object> error() {
        Map<String, Object> errorMap = new HashMap<>();

        Map<String, Object> errorObject = new HashMap<>();
        errorObject.put("message", "unsupported action!!!");

        errorMap.put("Error", errorObject);
        return errorMap;
    }
}
