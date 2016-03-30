package com.yaoli.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Area;
import com.yaoli.common.CustomPropertyConfigurer;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IStatisticDayService;
import com.yaoli.util.ExcelView;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.StatisticDayVO;
import com.yaoli.vo.TableTitleVO;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Resource
	private IAreaService iAreaService;
	
	@Resource
	private IStatisticDayService iStatisticDayService;
	
	
	@RequestMapping("/gotoquxiandayreport.do")
	public String gotoquxiandayReport(Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);
		return "/report/quxiandayreport";
	}
	
	@RequestMapping("/gotoquxianmonthreport.do")
	public String gotoquxianMonthReport(Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);
		return "/report/quxianmonthreport";
	}
	
	@RequestMapping("/gotoquxianyearreport.do")
	public String gotoquxianYearReport(Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);
		return "/report/quxianyearreport";
	}
	
	@RequestMapping("/getxtoken.do")
	public void getXtoken(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, String> map = CustomPropertyConfigurer.getProperties();
		
		String sysno = map.get("systemno");
		List<List<TableTitleVO>> lists = new ArrayList<List<TableTitleVO>>();
		if(sysno.equals("1")){
			List<TableTitleVO> tableTitleVOsTitle = new ArrayList<TableTitleVO>();
/*			TableTitleVO tableTitleTestingTime = new TableTitleVO();
			tableTitleTestingTime.setField("sewagename");
			tableTitleTestingTime.setTitle("污水站点");
			tableTitleTestingTime.setRowspan(2);
			tableTitleVOsTitle.add(tableTitleTestingTime);*/
			
			TableTitleVO tableTitleVO;
			for (int i = 8; i <= 21; i++) {
				tableTitleVO = new TableTitleVO();
				String key = "equipment"+i+"name";
				String value = map.get(key);
				tableTitleVO.setField(key);
				tableTitleVO.setTitle(value);
				tableTitleVO.setColspan(2);
				tableTitleVOsTitle.add(tableTitleVO);
			}
			
			
			
			//运行时间，故障时间
			List<TableTitleVO> tableTitleVOsProperties = new ArrayList<TableTitleVO>();
			TableTitleVO tableTitleVOproperty;
			for (int i = 8; i <= 21; i++) {
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("equip"+i+"normaltime");
				tableTitleVOproperty.setTitle("运行时间");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
				
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("equip"+i+"abnormaltime");
				tableTitleVOproperty.setTitle("故障时间");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
			}
			
			//--------------以上是设备统计，下面开始水质--------------------------
			for (int i = 1; i <= 14; i++) {
				tableTitleVO = new TableTitleVO();
				String key = "detection"+i+"name";
				String value = map.get(key);
				tableTitleVO.setField(key);
				tableTitleVO.setTitle(value);
				tableTitleVO.setColspan(3);
				tableTitleVOsTitle.add(tableTitleVO);
			}
			tableTitleVO = new TableTitleVO();
			tableTitleVO.setField("temp");
			tableTitleVO.setTitle("&nbsp;&nbsp;");
			tableTitleVO.setRowspan(2);
			tableTitleVOsTitle.add(tableTitleVO);
			
			for (int i = 1; i <= 14; i++) {
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("detection"+i+"max");
				tableTitleVOproperty.setTitle("最大");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				//tableTitleVOproperty.setFormatter("function(value,row,index){if(typeof(row.dection"+i+"max)!='undefined'){return dection"+i+"max.toFixed(2);}}");
				tableTitleVOsProperties.add(tableTitleVOproperty);
				
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("detection"+i+"min");
				tableTitleVOproperty.setTitle("最小");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
				
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("detection"+i+"avg");
				tableTitleVOproperty.setTitle("平均");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
			}

			
			lists.add(tableTitleVOsTitle);
			lists.add(tableTitleVOsProperties);
			
			
			//下面添加水质
		}
		
		String jsons = JSON.toJSONString(lists);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(jsons);
	}
	
	/**
	 * 获取区县的日统计
	 * @param response
	 * @param request
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping("/getquxianstatisticbycondition.do")
	public void getQuxianStatisticByCondition(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		
		Date testingtime = request.getParameter("testingtime").equals("")?null:formater.parse(request.getParameter("testingtime"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("testingtime", formater.format(testingtime));
		
		List<StatisticDayVO> statisticDayVOs = null;
		int count = 0;
		
		statisticDayVOs = iStatisticDayService.getStatisticDayVOByCondition(map);
		count = iStatisticDayService.getStatisticDayTotalByCondition(map);
		
		//equip6normaltime equip6abnormaltime
		//equip6normaltime
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(statisticDayVOs);
		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	/**
	 * 导出日统计数据
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/getexcelexportbyday.do")
	public ModelAndView getExcelExportByDay(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date testingtime = request.getParameter("testingtime").equals("")?null:formater.parse(request.getParameter("testingtime"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("testingtime", formater.format(testingtime));
		
		List<StatisticDayVO> statisticDayVOs = null;
		int count = 0;
		
		statisticDayVOs = iStatisticDayService.getStatisticDayVO(map);
		count = iStatisticDayService.getStatisticDayTotal(map);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("statisticDayVOs", statisticDayVOs);
		parameter.put("count", count);
		
		return new ModelAndView(new ExcelView(), parameter); 
	}
	/**
	 * 获取区县的月统计
	 * @param response
	 * @param request
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping("/getquxianstatisticmonthbycondition.do")
	public void getQuxianStatisticMonthByCondition(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		
		String testingtime = request.getParameter("testingtime").equals("")?null:request.getParameter("testingtime");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("testingtime", testingtime);
		
		List<StatisticDayVO> statisticDayVOs = null;
		int count = 0;
		
		statisticDayVOs = iStatisticDayService.getStatisticMonthVOByCondition(map);
		count = iStatisticDayService.getStatisticMonthTotalByCondition(map);
		
		//equip6normaltime equip6abnormaltime
		//equip6normaltime
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(statisticDayVOs);
		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	/**
	 * 导出区县所有的月统计数据
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/getexcelexportbymonth.do")
	public ModelAndView getExcelExportByMonth(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		String testingtime = request.getParameter("testingtime").equals("")?null:request.getParameter("testingtime");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("testingtime", testingtime);
		
		List<StatisticDayVO> statisticDayVOs = null;
		
		statisticDayVOs = iStatisticDayService.getStatisticMonthVO(map);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("statisticDayVOs", statisticDayVOs);
		
		return new ModelAndView(new ExcelView(), parameter); 
	}
	
	/**
	 * 获取区县的年统计
	 * @param response
	 * @param request
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping("/getquxianstatisticyearbycondition.do")
	public void getQuxianStatisticYearByCondition(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		
		String testingtime = request.getParameter("testingtime").equals("")?null:request.getParameter("testingtime");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("testingtime", testingtime);
		
		List<StatisticDayVO> statisticDayVOs = null;
		int count = 0;
		
		statisticDayVOs = iStatisticDayService.getStatisticYearVOByCondition(map);
		count = iStatisticDayService.getStatisticYearTotalByCondition(map);
		
		//equip6normaltime equip6abnormaltime
		//equip6normaltime
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(statisticDayVOs);
		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	/**
	 * 导出区县所有的年统计数据
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/getexcelexportbyyear.do")
	public ModelAndView getExcelExportByYear(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		String testingtime = request.getParameter("testingtime").equals("")?null:request.getParameter("testingtime");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("testingtime", testingtime);
		
		List<StatisticDayVO> statisticDayVOs = null;
		
		statisticDayVOs = iStatisticDayService.getStatisticYearVO(map);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("statisticDayVOs", statisticDayVOs);
		
		return new ModelAndView(new ExcelView(), parameter); 
	}
}
