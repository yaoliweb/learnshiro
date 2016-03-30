package com.yaoli.service;

import com.yaoli.beans.MessageLinker;
import java.util.List;
import java.util.Map;

public interface IMessageLinkerService {
    int insert(MessageLinker record);

    int insertSelective(MessageLinker record);

    int updateByPrimaryKeySelective(MessageLinker record);

    int updateByPrimaryKey(MessageLinker record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<MessageLinker> selectByPaingAndCondition(Map<String, String> map);
    
    int deleteMessagerLinkerByAreaid(String areaid);
    
    List<MessageLinker> getMessageLinkers(String areaid);
    
}