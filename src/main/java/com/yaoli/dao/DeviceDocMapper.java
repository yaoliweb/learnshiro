package com.yaoli.dao;

import com.yaoli.beans.DeviceDoc;
import java.util.List;
import java.util.Map;

public interface DeviceDocMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceDoc record);

    int insertSelective(DeviceDoc record);

    DeviceDoc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceDoc record);

    int updateByPrimaryKey(DeviceDoc record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<DeviceDoc> selectByPaingAndCondition(Map<String, String> map);
    
    int getPaingAndConditionTotalCountBySewageid(Map<String, String> map);
    
    //获取所有的deviceDocs();
    List<DeviceDoc> getAllDeviceDocs();
}