package com.yaoli.service.impl;

import com.yaoli.beans.EquipRepairRecord;
import com.yaoli.dao.EquipRepairRecordMapper;
import com.yaoli.service.IEquipRepairRecordService;
import com.yaoli.vo.EquipRepairRecordVO;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("EquipRepairRecordServiceImpl")
public class EquipRepairRecordServiceImpl implements IEquipRepairRecordService {
    @Resource
    public EquipRepairRecordMapper equipRepairRecordMapper;

    public int deleteByPrimaryKey(Long id) {
        return equipRepairRecordMapper.deleteByPrimaryKey(id);
    }

    public int insert(EquipRepairRecord record) {
        return equipRepairRecordMapper.insert(record);
    }

    public int insertSelective(EquipRepairRecord record) {
        return equipRepairRecordMapper.insertSelective(record);
    }

    public EquipRepairRecord selectByPrimaryKey(Long id) {
        return equipRepairRecordMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(EquipRepairRecord record) {
        return equipRepairRecordMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(EquipRepairRecord record) {
        return equipRepairRecordMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return equipRepairRecordMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return equipRepairRecordMapper.getCountByParameterName(parameterName);
    }

    public List<EquipRepairRecordVO> selectByPaingAndCondition(Map<String, String> map) {
        return equipRepairRecordMapper.selectByPaingAndCondition(map);
    }

	@Override
	public int selectByPaingAndConditionTotalCount(Map<String, String> map) {
		return equipRepairRecordMapper.selectByPaingAndConditionTotalCount(map);
	}
}