package com.yaoli.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.mysql.fabric.xmlrpc.base.Data;
import com.yaoli.service.IDetectionDataService;
import com.yaoli.vo.DetectionDataVO;
import com.yaoli.vo.SewageVO;

@Controller
@RequestMapping("/statistic")
public class StatisticController {
	
	@Resource
	private IDetectionDataService iDetectionDataService;
	
	@RequestMapping("/gotostatisticdaywater.do")
	public String gotoStatisticDayWater(){
		return "/statistic/statisticdaywater";
	}
	
	@RequestMapping("/getstatisticdaywater.do")
	public void getStatisticDayWater(@RequestBody DetectionDataVO detectionDataVO,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		Integer sewageid =detectionDataVO.getSewageid()==-1?null:detectionDataVO.getSewageid(); //request.getParameter("sewageid").toString().equals("")?null:request.getParameter("sewageid");
		Date testingtime = detectionDataVO.getTestingtime();//request.getParameter("testingtime").toString().equals("")?null:request.getParameter("testingtime");
		String operationnum = detectionDataVO.getOperationnum();//request.getParameter("operationnum").toString().equals("")?null:request.getParameter("operationnum");
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		//Date testingDate = format.parse(testingtime);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sewageid", sewageid==null?null:String.valueOf(sewageid));
		map.put("testingtime", format.format(testingtime));
		map.put("operationnum", operationnum);
		
		List<DetectionDataVO> detectionDataList = null;
		//表明是当天的数据 从detection_data表中获取
		if(format.format(testingtime).toString().equals(format.format(date).toString())){
			detectionDataList = iDetectionDataService.getStatisticDayWater(map);
		}else {//从statistic表中获取
			detectionDataList = iDetectionDataService.getStatisticDayWaterFromStatisticTable(map); 
		}
		
		
		String json = JSON.toJSONString(detectionDataList);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	
	/**
	 * 进入获取每月处理水量页面
	 * @return
	 */
	@RequestMapping("/gotostatisticmonthwater.do")
	public String gotoStatisticMonthWater(){
		return "/statistic/statisticmonthwater";
	}
	
	/**
	 * 获取每月处理水量
	 * @param detectionDataVO
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/getstatisticmonthwater.do")
	public void getStatisticMonthWater(@RequestBody DetectionDataVO detectionDataVO,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		Integer sewageid =detectionDataVO.getSewageid()==-1?null:detectionDataVO.getSewageid(); //request.getParameter("sewageid").toString().equals("")?null:request.getParameter("sewageid");
		Date testingtime = detectionDataVO.getTestingtime();//request.getParameter("testingtime").toString().equals("")?null:request.getParameter("testingtime");
		String operationnum = detectionDataVO.getOperationnum().equals("")?null:detectionDataVO.getOperationnum();//request.getParameter("operationnum").toString().equals("")?null:request.getParameter("operationnum");
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		//Date testingDate = format.parse(testingtime);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sewageid", sewageid==null?null:String.valueOf(sewageid));
		map.put("testingtime", format.format(testingtime));
		map.put("operationnum", operationnum);
		
		List<DetectionDataVO> detectionDataList = iDetectionDataService.getStatisticMonthWater(map);
		
		String json = JSON.toJSONString(detectionDataList);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	
	/**
	 * 进入获取每年处理水量页面
	 * @return
	 */
	@RequestMapping("/gotostatisticyearwater.do")
	public String gotoStatisticYearWater(){
		return "/statistic/statisticyearwater";
	}
	
	/**
	 * 获取每年处理水量
	 * @param detectionDataVO
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/getstatisticyearwater.do")
	public void getStatisticYearWater(@RequestBody DetectionDataVO detectionDataVO,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		Integer sewageid =detectionDataVO.getSewageid()==-1?null:detectionDataVO.getSewageid(); //request.getParameter("sewageid").toString().equals("")?null:request.getParameter("sewageid");
		Date testingtime = detectionDataVO.getTestingtime();//request.getParameter("testingtime").toString().equals("")?null:request.getParameter("testingtime");
		String operationnum = detectionDataVO.getOperationnum().equals("")?null:detectionDataVO.getOperationnum();//request.getParameter("operationnum").toString().equals("")?null:request.getParameter("operationnum");
		
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		//Date testingDate = format.parse(testingtime);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sewageid", sewageid==null?null:String.valueOf(sewageid));
		map.put("testingtime", format.format(testingtime));
		map.put("operationnum", operationnum);
		
		List<DetectionDataVO> detectionDataList = iDetectionDataService.getStatisticYearWater(map);//iDetectionDataService.getStatisticMonthWater(map);
		
		String json = JSON.toJSONString(detectionDataList);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
}
