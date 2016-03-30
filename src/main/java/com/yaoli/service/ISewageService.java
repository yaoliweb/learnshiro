package com.yaoli.service;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.DetectionDataAbnormal;
import com.yaoli.beans.RunData;
import com.yaoli.beans.Sewage;
import com.yaoli.vo.DetectionDataAbnormalVO;
import com.yaoli.vo.SewageMonitorVO;
import com.yaoli.vo.SewageVO;

public interface ISewageService {
	public List<Sewage> getAllSewages();
	
    public List<Sewage> getSewagesByAreaId(int areaid);

	public List<Sewage> getSewagesBySearchNameOrId(String condition);
	
	public Sewage selectByPrimaryKey(Integer sewageid);
	
	public SewageMonitorVO getSewageMonitorVOBySewageId(Integer sewageid);
	
	public List<SewageVO> getSewagesByPaging(Map<String, String> map);
	
	public int getTotalSewageCount();
	
	public int insert(Sewage record);
	
	public int insertSelective(Sewage record);
	
	int deleteByPrimaryKey(Integer sewageid);
	
	public int getSewageCountLinktoAreaBy(Integer areaId);
	
    //获取最新的站点信息和该站点下面的水质信息
    public List<Sewage> getLatestSewageDetectionData();
    
    public Sewage getLatestSewageDetectionDataBySewageId(int sewageId);
    
    //获取水量总和 detection8字段
    public double getSewageTotalWaterFlow(int sewageId);
    
    //获取断电断水
    public List<Sewage> getLatestSewageWithoutElectricAndWater();
    
    List<Sewage> getLatestSewageWithoutElectricAndWaterByAreaid(int areaid);
    
    
    //分页获得断电断线
    List<Sewage> getWithoutElectricAndWaterByPaingAndCondition(Map<String, String> map);
    
    //分页获得断电断线
    int getWithoutElectricAndWaterPaingAndConditionTotal(Map<String, String> map);
    
    //定时任务 获取 站点昨天是否有数据统计
    int getSewageHasRecordYesterday(int sewageid);
    
}
