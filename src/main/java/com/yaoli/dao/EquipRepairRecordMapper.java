package com.yaoli.dao;

import com.yaoli.beans.EquipRepairRecord;
import com.yaoli.vo.EquipRepairRecordVO;

import java.util.List;
import java.util.Map;

public interface EquipRepairRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EquipRepairRecord record);

    int insertSelective(EquipRepairRecord record);

    EquipRepairRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EquipRepairRecord record);

    int updateByPrimaryKey(EquipRepairRecord record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<EquipRepairRecordVO> selectByPaingAndCondition(Map<String, String> map);
    
    int selectByPaingAndConditionTotalCount(Map<String, String> map);
}