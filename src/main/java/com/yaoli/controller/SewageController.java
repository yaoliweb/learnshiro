package com.yaoli.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Area;
import com.yaoli.beans.DetectionData;
import com.yaoli.beans.RunData;
import com.yaoli.beans.Sewage;
import com.yaoli.common.CustomPropertyConfigurer;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IDetectionDataAbnormalService;
import com.yaoli.service.IDetectionDataService;
import com.yaoli.service.IResultStatisticsService;
import com.yaoli.service.IRunDataAbnormalService;
import com.yaoli.service.IRunDataService;
import com.yaoli.service.ISewageService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SewageVOUtils;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.DetectionDataVO;
import com.yaoli.vo.SewageMonitorVO;
import com.yaoli.vo.SewageVO;

@Controller
@RequestMapping("/monitor")
public class SewageController {

	@Resource
	public IAreaService isAreaService;

	@Resource
	public ISewageService iSewageService;
	
	@Resource
	public IDetectionDataService iDetectionDataService;
	
	//结果统计不需要了
	@Resource
	public IResultStatisticsService iResultStatisticsService;
	
	@Resource
	public IDetectionDataAbnormalService iDetectionDataAbnormalService;
	
	@Resource
	public IRunDataService iRunDataService;

	public List<Sewage> cacheSewages;
	
	@Resource
	public IRunDataAbnormalService iRunDataAbnormalService;

	/**
	 * 实时监控 进入地图
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/intomapmonitor")
	public String mapMonitor(HttpServletRequest request, Model model) {
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);
		return "/monitor/gmap";
	}

	/**
	 * 站点监控 站点监控主界面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	@RequestMapping("/getallsewages.do")
	public void getAllSewages(HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<Sewage> sewages = iSewageService.getAllSewages();
		
		List<DetectionData> detectionDatas = iDetectionDataService.getLatestSewageDetectionData();//水质异常问题
		List<RunData> runDatas  = iRunDataService.getLatestSewageRunData();//获取最新的运行情况 可能是昨天的 数据，从而显示的是故障
		List<Sewage> withoutEletrictAndWater = iSewageService.getLatestSewageWithoutElectricAndWater();//断电断线 今天到目前为止没有数据就是断电断线

		
		for (Sewage sewage : sewages) {
			//以下三个循环不可以改变顺序，断电断线<设备故障<水质问题
			for (Sewage ew : withoutEletrictAndWater) {
				if(ew.getSewageid().equals(sewage.getSewageid())){
					sewage.setIsAbnormal(3);//断线断线
					break;
				}
			}
			for (RunData runData : runDatas) {
				if(sewage.getSewageid().equals(runData.getSewageid())){
					if (SewageVOUtils.rundataisAbnormal(runData)) {
						sewage.setIsAbnormal(2);//表示设备有问题
						break;
					}
				}
			}
			for (DetectionData detectionData : detectionDatas) {
				if(sewage.getSewageid().toString().equals(detectionData.getSewageid().toString())){
					if(detectionData.getSewageid().toString().equals("172")){
						System.out.println("asdfasdfas");
					}
					if(SewageVOUtils.detectiondataisAbnormal(sewage, detectionData)==true){
						sewage.setIsAbnormal(1);//表示水质有问题
						break;
					}
				}
			}
		}
		
		
		String jsons = JSON.toJSONString(sewages);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(jsons);
	}

	
	/**
	 * 实时监控  选择areaid后  根据areaid获取该区域下的所有站点信息
	 * 
	 * 类似于getAllSewages
	 * @param sewageVO
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	@RequestMapping("/getsewagesbyareaid.do")
	public void getSewagesByAreaId(@RequestBody SewageVO sewageVO, HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<Sewage> sewages = iSewageService.getSewagesByAreaId(sewageVO.getAreaid());
		
		List<DetectionData> detectionDatas = iDetectionDataService.getLatestSewageDetectionDataByAreaId(sewageVO.getAreaid());
		List<RunData> runDatas  = iRunDataService.getLatestSewageRunDataByAreaid(sewageVO.getAreaid());
		List<Sewage> withoutEletrictAndWater =  iSewageService.getLatestSewageWithoutElectricAndWaterByAreaid(sewageVO.getAreaid());

		
		for (Sewage sewage : sewages) {
			//以下三个循环不可以改变顺序，断电断线<设备故障<水质问题
			for (Sewage ew : withoutEletrictAndWater) {
				if(ew.getSewageid().equals(sewage.getSewageid())){
					sewage.setIsAbnormal(3);//断线断线
					break;
				}
			}
			for (RunData runData : runDatas) {
				if(sewage.getSewageid().equals(runData.getSewageid())){
					if (SewageVOUtils.rundataisAbnormal(runData)) {
						sewage.setIsAbnormal(2);//表示设备有问题
						break;
					}
				}
			}
			for (DetectionData detectionData : detectionDatas) {
				if(sewage.getSewageid().toString().equals(detectionData.getSewageid().toString())){
					if(detectionData.getSewageid().toString().equals("172")){
						System.out.println("asdfasdfas");
					}
					if(SewageVOUtils.detectiondataisAbnormal(sewage, detectionData)==true){
						sewage.setIsAbnormal(1);//表示水质有问题
						break;
					}
				}
			}
		}
		
		Area area = isAreaService.selectByPrimaryKey(sewageVO.getAreaid());
		
		SewageVO sewage = new SewageVO();
		
		sewage.setAreaName(area.getName());
		
		if(area.getCoordinatex() != null){
			sewage.setCoordinatex(area.getCoordinatex());
			sewage.setCoordinatey(area.getCoordinatey());
		}
		sewage.setSewages(sewages);
		
		String jsons = JSON.toJSONString(sewage);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(jsons);
	}
	
	/**
	 * 实时监控 根据地点或者运营编号搜索
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getSewagesBySearchNameOrId.do")
	public void getSewagesByAreaNameOrYunyinNO(@RequestBody SewageVO sewageVO,HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Sewage> sewages = iSewageService.getSewagesBySearchNameOrId(sewageVO.getSearchNameOrId());
		
		if(cacheSewages == null){
			cacheSewages = iSewageService.getAllSewages();
		}
		
		sewageVO.setSewages(cacheSewages);
		
		sewageVO.setSearchResult(sewages);
		
		String jsons = JSON.toJSONString(sewageVO);
		
		response.setContentType("text/html; charset=UTF-8");
		
		response.getWriter().write(jsons);
	}
	
	/**
	 * 实时监控 获取站点的运行信息，即在地图上点击图标，弹出站点信息
	 * @param sewageVO
	 * @param request
	 * @param response
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping("/getlatestdetectiondata.do")
	public void getLatestDetectionData(@RequestBody SewageVO sewageVO,HttpServletRequest request,HttpServletResponse response)throws IllegalAccessException, Exception{
		int id = sewageVO.getSewageid();
		
		//获取该站点的信息
		Sewage sewage = iSewageService.selectByPrimaryKey(id);
		
		//获取该站点最新水质信息
		DetectionData detectionData = iDetectionDataService.getLatestDetectionData(id);
		
		//获取该站点最新设备信息
		RunData runData = iRunDataService.getLatestRunData(id); 
		
		//water 获取每日流量总和信息，根据detection6求和所得
		double sewageDayTotalWaterFlow = iSewageService.getSewageTotalWaterFlow(id); 
		
		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

		SewageMonitorVO sewageMonitorVO = mapper.map(sewage, SewageMonitorVO.class);
		
		//设置设备名称
		SewageVOUtils.setEquipmentName(sewageMonitorVO,runData);
		
		boolean rundataIsAbnormal = SewageVOUtils.rundataisAbnormal(runData);
		
		boolean detectionDataIsAbnormal = SewageVOUtils.detectiondataisAbnormal(sewage, detectionData);
		
		if(rundataIsAbnormal == true){
			sewageMonitorVO.setRunStateM("设备故障");
		}
		if(detectionDataIsAbnormal == true){
			sewageMonitorVO.setRunStateM("水质异常");
		}
		
		String html = SewageVOUtils.getSewageHTML(sewageMonitorVO,detectionData,runData,sewage,sewageDayTotalWaterFlow);
		
        JsonMessage jsonMessage  = new JsonMessage();
        jsonMessage.setMessage(html);
		String jsons = JSON.toJSONString(jsonMessage);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(jsons);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//---------------------------------以上是实时监控-------------------------------------//
	

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 站点监控
	 * @param request
	 * @param response
	 */
	@RequestMapping("/sewagemonitor.do")
	public String sewageMonitor(HttpServletRequest request,HttpServletResponse response,Model model){
		List<Area> areaList= isAreaService.getAllAreas();
		model.addAttribute("areaList", areaList);

		return "/monitor/sewagemonitor";
	}
	
	@RequestMapping("/ajaxgetsewagebyareaid.do")
	public void ajaxGetSewagesByAreaId(@RequestBody SewageVO sewageVO,HttpServletResponse response,HttpServletRequest request) throws IOException{
		int areaid = sewageVO.getAreaid();
		List<Sewage> sewages = iSewageService.getSewagesByAreaId(areaid);
		String json = JSON.toJSONString(sewages);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	public static void main(String[] args) {
		for (int i = 1; i < 15; i++) {
			System.out.println("private Float detection"+i+";");
			System.out.println("private Float detection"+i+"dl;");
		    System.out.println("private Float detection"+i+"ul;");;
		}
	}
	@RequestMapping("/ajaxgetsewageruninfobysewageid.do")
	public void ajaxGetSewageRunInfoSewageId(@RequestBody SewageVO sewageVO,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		//站点Id
		int sewageid = sewageVO.getSewageid();
		
		/*获取站点信息*/
		SewageMonitorVO sewageMonitorVO = iSewageService.getSewageMonitorVOBySewageId(sewageid);
		
		RunData latestRunDataInfo = iRunDataService.getLatestRunData(sewageid);
		
		DetectionData latestDetectionData = iDetectionDataService.getLatestDetectionData(sewageid);
		
/*		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
		if(latestDetectionData!=null){
			sewageMonitorVO = mapper.map(latestDetectionData, SewageMonitorVO.class);
		}
		if(latestRunDataInfo != null){
			sewageMonitorVO = mapper.map(latestRunDataInfo, SewageMonitorVO.class);
		}*/
		
		Map<String,String> properties = CustomPropertyConfigurer.getProperties();
		
		try {
			Class<?> rundataClass = Class.forName("com.yaoli.beans.RunData");
			Class<?> sewageMonitorVoClass = Class.forName("com.yaoli.vo.SewageMonitorVO");
			
			String systemNO = properties.get("systemno");
			
			if(systemNO.equals("1")){
				if(latestRunDataInfo != null){
					for (int i = 6; i <= 21; i++) {
						Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment11state
						Object stateObject = method.invoke(latestRunDataInfo);
						int state = stateObject == null ? -1:Integer.valueOf(stateObject.toString());
						if(state != -1){//表示 state不为空，即有该数据;并且下面进行赋值
							if(state == 3){
								sewageMonitorVO.setRunStateM("运行故障");
								break;
							}
						}
					}
				}else {
					sewageMonitorVO.setRunStateM("未检测到设备运行状态");
				}
				
				
				if(latestDetectionData != null){
					Class<?> detectionDataClass = Class.forName("com.yaoli.beans.DetectionData");
					for (int i = 1; i <= 14; i++) {
						//在这里添加不进行判断的地方
						if(i != 4 && i != 6 && i !=7 && i != 8 && i!=9){
							//设置 detection的下限 等
							Method getDetectiondl = sewageMonitorVoClass.getDeclaredMethod("getDetection"+i+"dl");
							Double detectiondlValue = getDetectiondl.invoke(sewageMonitorVO) == null?null:Double.valueOf(getDetectiondl.invoke(sewageMonitorVO).toString());
							
							
							//设置 detectiondl上限 等
							Method getDetectionul = sewageMonitorVoClass.getDeclaredMethod("getDetection"+i+"ul");
							Double detectionulValue = getDetectionul.invoke(sewageMonitorVO) == null?null:Double.valueOf(getDetectionul.invoke(sewageMonitorVO).toString());
							
							Method getDetection = detectionDataClass.getDeclaredMethod("getDetection"+i);
							Double detection = getDetection.invoke(latestDetectionData) == null?null:Double.valueOf(getDetection.invoke(latestDetectionData).toString());
							
							//将detection的值放入到 sewageMonitorVO 中
							if(detection != null){
								Field sewageMonitorVOgetDetectionField = sewageMonitorVoClass.getDeclaredField("detection"+i);
								sewageMonitorVOgetDetectionField.setAccessible(true);
								sewageMonitorVOgetDetectionField.set(sewageMonitorVO, detection);
							}

							if(detectionulValue != null && detectiondlValue !=null){
								if(detectiondlValue < detection || detection > detectionulValue){
									sewageMonitorVO.setRunStateM("水质异常");
								}
							}
						}
					}
				}else {
					sewageMonitorVO.setRunStateM("未检测到水质状态");
				}
				
				if(latestDetectionData == null && latestRunDataInfo == null){
					sewageMonitorVO.setRunStateM("未检测到设备和水质状态");
				}
			}
			
			//设置最后更新时间
			if(latestDetectionData != null){
				sewageMonitorVO.setLastUpdatetime(latestDetectionData.getTestingtime());	
			}else {
				if(latestDetectionData == null && latestRunDataInfo == null){
					sewageMonitorVO.setLastUpdatetime(null);	
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		
		//water 获取每日流量总和信息，根据detection6求和所得
		double sewageDayTotalWaterFlow = iSewageService.getSewageTotalWaterFlow(sewageid);
		sewageMonitorVO.setDayWater(String.valueOf(sewageDayTotalWaterFlow));
		if(sewageMonitorVO.getReducenh3n() != null){
			sewageMonitorVO.setReducenh3n(sewageDayTotalWaterFlow*sewageMonitorVO.getReducenh3n());
		}
		if(sewageMonitorVO.getReducep() != null){
			sewageMonitorVO.setReducep(sewageDayTotalWaterFlow*sewageMonitorVO.getReducep());
		}
		if(sewageMonitorVO.getReducecod() != null){
			sewageMonitorVO.setReducecod(sewageDayTotalWaterFlow*sewageMonitorVO.getReducecod());
		}
		String json = JSON.toJSONString(sewageMonitorVO);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	
	
	
	
	@RequestMapping("/ajaxgetlatestdetectiondata.do")
	public void ajaxGetLatestDetectionData(@RequestBody SewageVO sewageVO,HttpServletRequest request,HttpServletResponse response)throws IOException{
		int sewageid = sewageVO.getSewageid();
		DetectionData detectionData = iDetectionDataService.getLatestDetectionData(sewageid);
		String json = JSON.toJSONString(detectionData);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	@RequestMapping("/ajaxget5info.do")
	public void ajaxget5info(@RequestBody SewageVO sewageVO ,HttpServletRequest request, HttpServletResponse response) throws IOException{
		int sewageid = sewageVO.getSewageid();
		List<DetectionDataVO> detectionDataList = iDetectionDataService.gettop5info(sewageid);
		String json = JSON.toJSONString(detectionDataList);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}
	
	
	@RequestMapping("/gotoshowsewages.do")
	public String gotosewages(){
		return "/areas/showsewages";
	}
	
	@RequestMapping("/getsewages.do")
	public void showSewages(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		List<SewageVO> sewageVOList = iSewageService.getSewagesByPaging(map);
	
		
		int count = iSewageService.getTotalSewageCount();
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(sewageVOList);
		
		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	@RequestMapping("/gotoaddsewage.do")
	public String gotoAddNewSewage(){
		return "/areas/addnewsewage";
	}
	
	@RequestMapping("/addnewsewage.do")
	public void addNewSewage(@RequestBody SewageVO sewageVO ,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
		//转换成Sewage
		Sewage sewage = mapper.map(sewageVO, Sewage.class);
		int count = iSewageService.insertSelective(sewage);
		
		if(count != 1){
			throw new Exception("添加污水站点失败");
		}
		JsonMessage jsondata = new JsonMessage();
		jsondata.setKey("pass");
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}
	
	@RequestMapping("/deleteselectedsewages.do")
	public void deleteSelectedSewages(@RequestBody SewageVO sewageVO,HttpServletResponse response,HttpServletRequest request) throws Exception{
		List<Integer> idList = sewageVO.getSelectIds();
		for (int i = 0; i < idList.size(); i++) {
			int count = iSewageService.deleteByPrimaryKey(idList.get(i));
			if(count != 1){
				throw new Exception("删除污水站点失败！");
			}
		}
		JsonMessage jsondata = new JsonMessage();
		jsondata.setKey("pass");
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}
	
	@RequestMapping("/ajaxgetsewagebysewageid.do")
	public void ajaxGetSewageBySewageid(@RequestBody Sewage sewage,HttpServletResponse response,HttpServletRequest request) throws IOException{
		Sewage sewage2 = iSewageService.selectByPrimaryKey(sewage.getSewageid());
		JsonMessage jsondata = new JsonMessage();
		if(sewage2!=null){
			jsondata.setKey("pass");
			jsondata.setMessage(sewage2.getControlmethod().toString());
		}else {
			jsondata.setKey("null");
		}
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}
}
