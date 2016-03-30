package com.yaoli.service.impl;

import com.yaoli.beans.StatisticDay;
import com.yaoli.dao.StatisticDayMapper;
import com.yaoli.service.IStatisticDayService;
import com.yaoli.vo.StatisticDayVO;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("StatisticDayServiceImpl")
public class StatisticDayServiceImpl implements IStatisticDayService {
    @Resource
    public StatisticDayMapper statisticDayMapper;

    public int deleteByPrimaryKey(Integer id) {
        return statisticDayMapper.deleteByPrimaryKey(id);
    }

    public int insert(StatisticDay record) {
        return statisticDayMapper.insert(record);
    }

    public int insertSelective(StatisticDay record) {
        return statisticDayMapper.insertSelective(record);
    }

    public StatisticDay selectByPrimaryKey(Integer id) {
        return statisticDayMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(StatisticDay record) {
        return statisticDayMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(StatisticDay record) {
        return statisticDayMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return statisticDayMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return statisticDayMapper.getCountByParameterName(parameterName);
    }

    public List<StatisticDay> selectByPaingAndCondition(Map<String, String> map) {
        return statisticDayMapper.selectByPaingAndCondition(map);
    }

	@Override
	public List<StatisticDayVO> getStatisticDayVOByCondition(
			Map<String, String> map) {
		return statisticDayMapper.getStatisticDayVOByCondition(map);
	}

	@Override
	public int getStatisticDayTotalByCondition(Map<String, String> map) {
		return statisticDayMapper.getStatisticDayTotalByCondition(map);
	}

	@Override
	public List<StatisticDayVO> getStatisticMonthVOByCondition(
			Map<String, String> map) {
		return statisticDayMapper.getStatisticMonthVOByCondition(map);
	}

	@Override
	public int getStatisticMonthTotalByCondition(Map<String, String> map) {
		return statisticDayMapper.getStatisticMonthTotalByCondition(map);
	}

	@Override
	public List<StatisticDayVO> getStatisticYearVOByCondition(
			Map<String, String> map) {
		return statisticDayMapper.getStatisticYearVOByCondition(map);
	}

	@Override
	public int getStatisticYearTotalByCondition(Map<String, String> map) {
		return statisticDayMapper.getStatisticYearTotalByCondition(map);
	}

	@Override
	public List<StatisticDayVO> getStatisticDayVO(Map<String, String> map) {
		return statisticDayMapper.getStatisticDayVO(map);
	}

	@Override
	public int getStatisticDayTotal(Map<String, String> map) {
		return statisticDayMapper.getStatisticDayTotal(map);
	}

	@Override
	public List<StatisticDayVO> getStatisticMonthVO(Map<String, String> map) {
		return statisticDayMapper.getStatisticMonthVO(map);
				
	}

	@Override
	public List<StatisticDayVO> getStatisticYearVO(Map<String, String> map) {
		return statisticDayMapper.getStatisticYearVO(map);
	}
}