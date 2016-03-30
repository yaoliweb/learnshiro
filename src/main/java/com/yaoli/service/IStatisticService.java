package com.yaoli.service;

import com.yaoli.beans.Statistic;
import java.util.List;
import java.util.Map;

public interface IStatisticService {
    int deleteByPrimaryKey(Long id);

    int insert(Statistic record);

    int insertSelective(Statistic record);

    Statistic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Statistic record);

    int updateByPrimaryKey(Statistic record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<Statistic> selectByPaingAndCondition(Map<String, String> map);
    
    //根据站点id 执行昨天的统计任务
    int executeStatisticYesterday(int sewageid);
    
}