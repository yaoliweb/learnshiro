package com.yaoli.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.mysql.fabric.xmlrpc.base.Array;
import com.yaoli.beans.DetectionData;
import com.yaoli.beans.RunData;
import com.yaoli.beans.RunDataAbnormal;
import com.yaoli.common.CustomPropertyConfigurer;
import com.yaoli.service.IDetectionDataService;
import com.yaoli.service.IRunDataAbnormalService;
import com.yaoli.service.IRunDataService;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.RunDataAbnormalVO;
import com.yaoli.vo.SewageVO;
import com.yaoli.vo.TableTitleVO;

@Controller
@RequestMapping("/search")
public class SewageSearchController {
	
	@Resource
	private IRunDataService iRunDataService;
	
	@Resource
	private IDetectionDataService iDetectionDataService;
	
	@Resource
	private IRunDataAbnormalService iRunDataAbnormalService;
	
	@RequestMapping("/test.do")
	public String dotest(Model model,HttpServletRequest request,HttpServletResponse response){
		return "pages/sewageinquire/jqgridtest";
	}
	
	@RequestMapping("/gotoshowequipmentrunrecord.do")
	public String gotoshowEquipmentRunRecord(){
		return "/sewagesearch/showequipmentrunrecord";
	}
	
	//设备运行记录获取x轴 标题
	@RequestMapping("/getsearchxtoken.do")
	public void getSearchXToken(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, String> map = CustomPropertyConfigurer.getProperties();
		
		List<TableTitleVO> listTableTitleVOs = new ArrayList<TableTitleVO>();
		
		TableTitleVO tableTitleTestingTime = new TableTitleVO();
		tableTitleTestingTime.setKey("testingtime");
		tableTitleTestingTime.setValue("检测时间");
		listTableTitleVOs.add(tableTitleTestingTime);
		
		TableTitleVO tableTitleVO;
		for (int i = 6; i <= 21; i++) {
			tableTitleVO = new TableTitleVO();
			String key = "equipment"+i+"name";
			String value = map.get(key);
			String key2 = "equipment"+i+"state";
			tableTitleVO.setKey(key2);
			tableTitleVO.setValue(value);
			listTableTitleVOs.add(tableTitleVO);
		}
		
		String jsons = JSON.toJSONString(listTableTitleVOs);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(jsons);
	}
	
	

	@RequestMapping("/gotoshowwatermonitorrecord.do")
	public String gotoShowWaterMonitorRecord(){
		return "/sewagesearch/showwatermonitorrecord";
	}
	@RequestMapping("/getsearchequipmentrunrecord.do")
	public void getSearchEquipmentRunRecord(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		String begintime = String.valueOf(request.getParameter("begintime")).equals("")?null:String.valueOf(request.getParameter("begintime"));
		String endtime = String.valueOf(request.getParameter("endtime")).equals("")?null:String.valueOf(request.getParameter("endtime"));
		String operationnum = String.valueOf(request.getParameter("operationnum")).equals("")?null:String.valueOf(request.getParameter("operationnum"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		map.put("sewageid", sewageid);
		map.put("begintime", begintime);
		map.put("endtime", endtime);
		map.put("operationnum", operationnum);
		
		List<RunData> sewageVOList = iRunDataService.getEquipmentRunDataRecord(map);
		
		
		int count = iRunDataService.getEquipmentRunDatasRecordTotal(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(sewageVOList);
		
		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	//水质检测记录获取x轴 标题
	@RequestMapping("/getwatermonitorxtoken.do")
	public void getWaterMonitorXToken(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, String> map = CustomPropertyConfigurer.getProperties();
		
		List<TableTitleVO> listTableTitleVOs = new ArrayList<TableTitleVO>();
		
		TableTitleVO tableTitleTestingTime = new TableTitleVO();
		tableTitleTestingTime.setKey("testingtime");
		tableTitleTestingTime.setValue("检测时间");
		listTableTitleVOs.add(tableTitleTestingTime);
		
		TableTitleVO tableTitleVO;
		for (int i = 1; i <= 14; i++) {
			tableTitleVO = new TableTitleVO();
			String key = "detection"+i+"name";
			String value = map.get(key);
			tableTitleVO.setKey("detection"+i);
			tableTitleVO.setValue(value);
			listTableTitleVOs.add(tableTitleVO);
		}
		
		String jsons = JSON.toJSONString(listTableTitleVOs);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(jsons);
	}
	
	
	@RequestMapping("/getsearchwaterrecord.do")
	public void getSearchWaterRecord(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		String begintime = String.valueOf(request.getParameter("begintime")).equals("")?null:String.valueOf(request.getParameter("begintime"));
		String endtime = String.valueOf(request.getParameter("endtime")).equals("")?null:String.valueOf(request.getParameter("endtime"));
		String operationnum = String.valueOf(request.getParameter("operationnum")).equals("")?null:String.valueOf(request.getParameter("operationnum"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		map.put("sewageid", sewageid);
		map.put("begintime", begintime);
		map.put("endtime", endtime);
		map.put("operationnum", operationnum);
		
		List<DetectionData> datas = iDetectionDataService.getDetectionDataRecord(map);
		
		int count = iDetectionDataService.getDetectionDataRecordTotal(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(datas);
		
		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	
	
	@RequestMapping("/gotoshowequipmentabnormalrecord.do")
	public String gotoShowEquipmentAbnormalRecord(){
		return "/sewagesearch/showequipmentabnormalrecord";
	}
	@RequestMapping("/showequipmentabnormalrecord.do")
	public void showWaterMonitorRecord(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		String begintime = String.valueOf(request.getParameter("begintime")).equals("")?null:String.valueOf(request.getParameter("begintime"));
		String endtime = String.valueOf(request.getParameter("endtime")).equals("")?null:String.valueOf(request.getParameter("endtime"));
		String operationnum = String.valueOf(request.getParameter("operationnum")).equals("")?null:String.valueOf(request.getParameter("operationnum"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		map.put("sewageid", sewageid);
		map.put("begintime", begintime);
		map.put("endtime", endtime);
		map.put("operationnum", operationnum);

		List<RunDataAbnormalVO> runDataVOs = iRunDataAbnormalService.getEquipmentAbnormalByPagingAndConditionMap(map);
		int count = iRunDataAbnormalService.getEquipmentAbnormalByPagingAndConditionMapTotalCount(map);
		Map<String, String> propertiesMap = CustomPropertyConfigurer.getProperties();
		for (RunDataAbnormalVO runDataAbnormalVO : runDataVOs) {
			runDataAbnormalVO.setEquipmentname(propertiesMap.get("equipment"+runDataAbnormalVO.getEquipmentno()+"name"));
		}
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(runDataVOs);
		
		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
}
