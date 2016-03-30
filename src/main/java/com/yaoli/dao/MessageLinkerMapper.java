package com.yaoli.dao;

import com.yaoli.beans.MessageLinker;
import java.util.List;
import java.util.Map;

public interface MessageLinkerMapper {
    int deleteByPrimaryKey();

    int insert(MessageLinker record);

    int insertSelective(MessageLinker record);

    MessageLinker selectByPrimaryKey();

    int updateByPrimaryKeySelective(MessageLinker record);

    int updateByPrimaryKey(MessageLinker record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<MessageLinker> selectByPaingAndCondition(Map<String, String> map);
    
    int deleteMessagerLinkerByAreaid(String areaid);
    
    List<MessageLinker> getMessageLinkers(String areaid);
}