package com.yaoli.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Area;
import com.yaoli.beans.DeviceDoc;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IDeviceDocService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.RunDataAbnormalVO;

@Controller
@RequestMapping("/device")
public class DeviceDocController {

	@Resource
	public IAreaService isAreaService;
	
	@Resource
	public IDeviceDocService iDeviceDocService;
	
	@RequestMapping("/gotoshowdevicedoc.do")
	public String gotoShowDeviceDoc(Model model){
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);
		return "/device/showdevicedoc";
	}
	
	@RequestMapping("/gotoadddevicedoc.do")
	public String gotoAddDeviceDoc(Model model){
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);
		return "/device/addnewdevice";
	}
	
	@RequestMapping("/adddevicedoc.do")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public void addDeviceDoc(@RequestBody DeviceDoc deviceDoc,HttpServletResponse response,HttpServletRequest request) throws IOException{
		int count = iDeviceDocService.insertSelective(deviceDoc);
		JsonMessage jsonMessage = new JsonMessage();
		
		if(count == 1){
			jsonMessage.setKey("pass");
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
	
	@RequestMapping("/getdevicedoc.do")
	public void getDeviceDoc(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));

		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("sewageid", sewageid);
		
		List<DeviceDoc> deviceDoc = iDeviceDocService.selectByPaingAndCondition(map);
		int count = iDeviceDocService.getPaingAndConditionTotalCountBySewageid(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(deviceDoc);

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
}
