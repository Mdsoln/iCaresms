package com.iCaresms.iCaresms.dao;

import com.iCaresms.iCaresms.Constants.SMSConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Envayasms {
    @Autowired
    IcareServiceImpl iCareService;
    @RequestMapping(value = "envayasms/handle-actions", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> handleRequest(
            @RequestParam(value  = "action") String action,
            @RequestParam(value  = "from",required =false) String from,
            @RequestParam(value  ="message",required = false) String message,
            @RequestParam(value  = "messageType",required = false) String messageType
    )throws Exception{

        Map<String,Object> response = new HashMap<>();

        if (SMSConstants.ACTION_INCOMING.equals(action)){
            iCareService.processIncomingAction(from, message, messageType);

        } else if (SMSConstants.ACTION_OUTGOING.equals(action)) {
            List<Map<String, Object>> eventsJson = iCareService.handleOutgoingSms();
            response.put("events", eventsJson);

        } else{
            response.put("Error", iCareService.error());
        }

        return response;
    }
}
