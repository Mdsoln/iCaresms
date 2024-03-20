package com.iCaresms.iCaresms.dao;

import java.util.List;
import java.util.Map;

public interface IcareService {
    void processIncomingAction(String from, String message, String type);

    public List<Map<String, Object>> handleOutgoingSms() throws Exception;

    public Map<String, Object> error();


}
