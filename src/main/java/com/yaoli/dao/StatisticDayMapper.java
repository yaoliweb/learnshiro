package com.yaoli.dao;

import com.yaoli.beans.StatisticDay;
import com.yaoli.vo.StatisticDayVO;

import java.util.List;
import java.util.Map;

public interface StatisticDayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StatisticDay record);

    int insertSelective(StatisticDay record);

    StatisticDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StatisticDay record);

    int updateByPrimaryKey(StatisticDay record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<StatisticDay> selectByPaingAndCondition(Map<String, String> map);
    
    //获取 日统计量
    List<StatisticDayVO> getStatisticDayVOByCondition(Map<String, String> map);
    int getStatisticDayTotalByCondition(Map<String, String> map);
    
    //获取全部的日统计量
    List<StatisticDayVO> getStatisticDayVO(Map<String, String> map);
    int getStatisticDayTotal(Map<String, String> map);

    
    
    //获取月统计量
    List<StatisticDayVO> getStatisticMonthVOByCondition(Map<String, String> map);
    int getStatisticMonthTotalByCondition(Map<String, String> map);
    //获取全部的月统计两
    List<StatisticDayVO> getStatisticMonthVO(Map<String, String> map);
    
    //获取年统计量
    List<StatisticDayVO> getStatisticYearVOByCondition(Map<String, String> map);
    int getStatisticYearTotalByCondition(Map<String, String> map);
    //获取全部的月统计两
    List<StatisticDayVO> getStatisticYearVO(Map<String, String> map);
}