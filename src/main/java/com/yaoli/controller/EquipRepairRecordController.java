package com.yaoli.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Area;
import com.yaoli.beans.DeviceDoc;
import com.yaoli.beans.EquipRepairRecord;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IDeviceDocService;
import com.yaoli.service.IEquipRepairRecordService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.EquipRepairRecordVO;

@Controller
@RequestMapping("/equiprepairrecord")
public class EquipRepairRecordController {
	@Resource
	public IAreaService isAreaService;
	
	@Resource
	public IDeviceDocService iDeviceDocService;
	
	@Resource
	public IEquipRepairRecordService iEquipRepairRecordService;
	
	@RequestMapping("/gotoequiprepairrecord.do")
	public String gotoEquipRepairRecord(Model model){
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);
		
		List<DeviceDoc> deviceDocs = iDeviceDocService.getAllDeviceDocs();
		model.addAttribute("deviceDocs", deviceDocs);
		return "/equiprepairrecord/addnewrepairrecord";
	}
	
	@RequestMapping("/addequiprepairrecord.do")
	public void addEquipRepairRecord(@RequestBody EquipRepairRecord record,HttpServletRequest request,HttpServletResponse response) throws IOException{
		int count = iEquipRepairRecordService.insertSelective(record);
		JsonMessage jsonMessage = new JsonMessage();
		if(count == 1){
			jsonMessage.setKey("pass");
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
	
	@RequestMapping("/showrepaired.do")
	public String showrepaired(HttpServletRequest request,HttpServletResponse response,Model model){
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);
		return "/equiprepairrecord/showrepairrecord";
	}
	
	@RequestMapping("/getrepairedrecord.do")
	public void getRepairedRecord(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));

		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("sewageid", sewageid);
		
		List<EquipRepairRecordVO> list = iEquipRepairRecordService.selectByPaingAndCondition(map);//selectByPaingAndCondition(map);//iDeviceDocService.selectByPaingAndCondition(map);
		int count = iEquipRepairRecordService.selectByPaingAndConditionTotalCount(map);//getPaingAndConditionTotalCountBySewageid(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(list);

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
}
