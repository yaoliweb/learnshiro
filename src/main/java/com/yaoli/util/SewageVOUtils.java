package com.yaoli.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.yaoli.beans.DetectionData;
import com.yaoli.beans.RunData;
import com.yaoli.beans.Sewage;
import com.yaoli.common.CustomPropertyConfigurer;
import com.yaoli.vo.SewageMonitorVO;

public class SewageVOUtils {
	
	/**
	 * 实时监控，弹出的信息框
	 * @param se
	 * @param de
	 * @param ru
	 * @param sewageDayTotalWaterFlow
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static String getSewageHTML(SewageMonitorVO se,DetectionData de,RunData runData,Sewage sewage,double sewageDayTotalWaterFlow) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		StringBuilder sb = new StringBuilder();
		sb.append(getWapper(se.getShortTitle()));
		
		Map<String, String> map = CustomPropertyConfigurer.getProperties();
		String systemNO = map.get("systemno");
		//如果系统参数是1，表明是江都的系统
		if(systemNO.equals("1")){
			Class<?> rundataClass = Class.forName("com.yaoli.beans.RunData");
			Class<?> sewageMonitorVoClass = Class.forName("com.yaoli.vo.SewageMonitorVO");
			Class<?> detectionDataClass = Class.forName("com.yaoli.beans.DetectionData");
			
			//获取运营编号
			sb.append("运营编号："+se.getOperationnum()+"</br>");
			
			String equipmentName = "";
			if(runData != null){
				for (int i = 6; i <= 21; i++) {
					Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment11state
					Object stateObject = method.invoke(runData);
					int state = (stateObject == null ? -1:Integer.valueOf(stateObject.toString()));
					if(state != -1){//表示 state不为空，即有该数据;设置设备名称。
						Method getRunDataState = sewageMonitorVoClass.getDeclaredMethod("getEquipment"+i+"Name");
						equipmentName = String.valueOf(getRunDataState.invoke(se));
						if (i == 6 || i == 7) {//两个液位计
							if(state == 8){
								sb.append(getWapper(getRedWapper(equipmentName+"：低低")));
							}else if(state == 1){
								sb.append(getWapper(getRedWapper(equipmentName+"：高高")));
							}else {
								if(state == 2){
									sb.append(getWapper(equipmentName+":高"));
								}else {//state == 4
									sb.append(getWapper(equipmentName+":低"));
								}
							}	
						}else {//其他设备
							if(state == 3){
								sb.append(getWapper(getRedWapper(equipmentName+"：故障")));
							}else {
								if(state == 1){
									sb.append(getWapper(equipmentName+":运行"));
								}else {//state == 2
									sb.append(getWapper(equipmentName+":停止"));
								}
							}	
						}
					}
				}
			}

			
			
			String detectionName;
			if(de != null){
				for (int i = 1; i <= 14; i++) {
					//在这里添加不进行判断的地方
					
					//设置 detection的下限 等
					Method getDetectiondl = sewageMonitorVoClass.getDeclaredMethod("getDetection"+i+"dl");
					Double detectiondlValue = getDetectiondl.invoke(se) == null?null:Double.valueOf(getDetectiondl.invoke(se).toString());
					
					
					//设置 detectiondl上限 等
					Method getDetectionul = sewageMonitorVoClass.getDeclaredMethod("getDetection"+i+"ul");
					Double detectionulValue = getDetectionul.invoke(se) == null?null:Double.valueOf(getDetectionul.invoke(se).toString());
					
					Method getDetection = detectionDataClass.getDeclaredMethod("getDetection"+i);
					Double detection = getDetection.invoke(de) == null?null:Double.valueOf(getDetection.invoke(de).toString());
					
					if (detectionulValue != null && detectiondlValue !=null && detection != null) {
						detectionName = map.get("detection"+i+"name");
						if (detectiondlValue > detection || detectionulValue < detection) {
							sb.append(getWapper(getOrangeWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection))));
						}else {
							sb.append(getWapper(detectionName+"："+new java.text.DecimalFormat("0.00").format(detection)));
						}
					}
				}
			}
		}
		
		sb.append(getWapper("日处理水量："+sewageDayTotalWaterFlow+"立方米"));
		if(se.getReducecod() != null){
			sb.append(getWapper("日削减COD量："+new java.text.DecimalFormat("0.00").format(sewageDayTotalWaterFlow*se.getReducecod())+"克"));
		}
		if(se.getReducenh3n() != null){
			sb.append(getWapper("日削减NH3-N量："+new java.text.DecimalFormat("0.00").format(sewageDayTotalWaterFlow*se.getReducenh3n())+"克"));
		}
		if(se.getReducep() != null){
			sb.append(getWapper("日削减总P量："+new java.text.DecimalFormat("0.00").format(se.getReducep()*sewageDayTotalWaterFlow)+"克"));
		}



		
        Subject subject = SecurityUtils.getSubject();
        List<String> roleList = new ArrayList<String>();
        roleList.add("7");//拥有管理员
        roleList.add("8");//用用超级管理员角色
        if(subject.hasAllRoles(roleList)){
        	
        }else {
			
		}
		return sb.toString();
	}
	
	public static String getWapper(String content){
		String wapper = "<font face='Arial'>"+content+"</font><br/>";
		return wapper;
	}
	public static String getRedWapper(String content){
		String wapper ="<font color='red'>"+content+"</font>";
		return wapper;
	}
	
	public static String getOrangeWapper(String content){
		String wapper ="<font color='orange'>"+content+"</font>";
		return wapper;
	}
	
	//------------------------------以下方法暂时用不到----------------------------//
	
	
	public static String getControlStrategy(String token,String machineToke){
		if(machineToke.equals("0") || machineToke.equals("3")){
			if (token.equals("1")) {
				return "手动控制";
			}else if(token.equals("2")){
				return "时间控制";
			}else if(token.equals("3")){
				return "溶解氧控制";
			}else {
				return "未知错误";
			}
		}else if(machineToke.equals("1") || machineToke.equals("2")){
			if (token.equals("1")) {
				return "手动控制";
			}else if(token.equals("2")){
				return "时间控制";
			}else {
				return "未知错误";
			}
		}else{
			return "未知错误";
		}
		
	}
	public static String getNowDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new java.util.Date());
	}
	
	public static String getGaugeState(String name,float data,String token){
		NumberFormat nf = new DecimalFormat("0.00");
		if(token.equals("1")){
			return nf.format(data)+"(正常)";
		}else if(token.equals("2")){
			return nf.format(data)+"(警告:"+name+"高于设定值)";
		}else if(token.equals("3")){
			return nf.format(data)+"(警告:"+name+"低于设定值)";
		}else {
			return "仪表未知错误";
		}
	}
	
	public static String getEquipmentState(String stateToke){
		if (stateToke.equals("1")) {
			return "设备正常【运行】";
		}else if(stateToke.equals("2")){
			return "设备正常【运行】";
		}else if(stateToke.equals("3")){
			return "设备故障";
		}else if(stateToke.equals("4")){
			return "设备未安装";
		}else {
			return "设备未知问题";
		}
	}
	
	public static String getGaugeRange(float down,float up){
		return String.valueOf(down)+" ~ "+ String.valueOf(up);
	}
	
	
	/**
	 * 判断设备是否有故障
	 * @param runData
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	public static boolean rundataisAbnormal(RunData runData) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException{
		Map<String, String> map = CustomPropertyConfigurer.getProperties();
		String systemNO = map.get("systemno");
		//如果系统参数是1，表明是江都的系统
		if(systemNO.equals("1")){
			Class<?> rundataClass = Class.forName("com.yaoli.beans.RunData");
			
			if(runData!= null){
				for (int i = 6; i <= 21; i++) {
					Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment11state
					Object stateObject = method.invoke(runData);
					int state = (stateObject == null ? -1:Integer.valueOf(stateObject.toString()));
					if(state != -1){//表示 state不为空，即有该数据;设置设备名称。
						if(i == 6 || i == 7){
							if(state == 1 || state == 8){
								return true;
							}
						}else {
							if(state == 3){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断水质是否有故障
	 * @param se
	 * @param de
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws NumberFormatException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	public static boolean detectiondataisAbnormal(Sewage sewage,DetectionData de) throws ClassNotFoundException, NumberFormatException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException{
		Map<String, String> map = CustomPropertyConfigurer.getProperties();
		String systemNO = map.get("systemno");
		
		//如果系统参数是1，表明是江都的系统
		if(systemNO.equals("1")){
			Class<?> detectionDataClass = Class.forName("com.yaoli.beans.DetectionData");
			Class<?> sewageClass = Class.forName("com.yaoli.beans.Sewage");
			if(de != null){
				for (int i = 1; i <= 14; i++) {
					//在这里添加不进行判断的地方
					
					//设置 detection的下限 等
					Method getDetectiondl = sewageClass.getDeclaredMethod("getDetection"+i+"dl");
					Double detectiondlValue = getDetectiondl.invoke(sewage) == null?null:Double.valueOf(getDetectiondl.invoke(sewage).toString());
					
					
					//设置 detectiondl上限 等
					Method getDetectionul = sewageClass.getDeclaredMethod("getDetection"+i+"ul");
					Double detectionulValue = getDetectionul.invoke(sewage) == null?null:Double.valueOf(getDetectionul.invoke(sewage).toString());
					
					Method getDetection = detectionDataClass.getDeclaredMethod("getDetection"+i);
					Double detection = getDetection.invoke(de) == null?null:Double.valueOf(getDetection.invoke(de).toString());
					
					//表明水质有问题
					if (detectionulValue != null && detectiondlValue !=null && detection != null) {
						if (detectiondlValue > detection || detectionulValue < detection) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * 设置设备名称
	 * @param sewageMonitorVO
	 * @param runData
	 * @throws ClassNotFoundException
	 * @throws Exception
	 * @throws IllegalAccessException
	 */
	public static void setEquipmentName(SewageMonitorVO sewageMonitorVO,RunData runData) throws ClassNotFoundException, Exception, IllegalAccessException{
		Map<String, String> map = CustomPropertyConfigurer.getProperties();
		
		String systemNO = map.get("systemno");
		
		
		//如果系统参数是1，表明是江都的系统
		if(systemNO.equals("1")){
			Class<?> rundataClass = Class.forName("com.yaoli.beans.RunData");
			Class<?> sewageMonitorVoClass = Class.forName("com.yaoli.vo.SewageMonitorVO");
			
			if(runData != null){
				for (int i = 6; i <= 21; i++) {
					if(runData != null){}
					Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment1state
					Object stateObject = method.invoke(runData);
					int state = (stateObject == null ? -1:Integer.valueOf(stateObject.toString()));
					if(state != -1){//表示 state不为空，即有该数据;设置设备名称。
						//设置设备名称 
						Field field2 = sewageMonitorVoClass.getDeclaredField("equipment"+i+"Name");
						field2.setAccessible(true);
						field2.set(sewageMonitorVO, (String)map.get("equipment"+i+"name"));
					}
				}
			}

		}
	}

	/*
    //获取角色值
    //Subject
    Subject subject = SecurityUtils.getSubject();
    List<String> roleList = new ArrayList<String>();
    roleList.add("7");//拥有管理员
    roleList.add("8");//用用超级管理员角色
    if(subject.hasAllRoles(roleList))
    {
        String ON = "<font face='Arial'>" + "运营编号：" + operationnum + "</font>" + "<br/>";
        html = "<b><font face='Arial'><a href='/CWS/pages/work/sewagecontrol2.faces?sewageid=" + id + "&sewagetitle=" + java.net.URLEncoder.encode(sewage.getShortTitle()) + "'>" + sewage.getName() + "</a></font></b>" + "<br/>"
               + ON
               + eqpstate1
               + eqpstate2
               + eqpstate3
               + detvalue1
               + detvalue2
               + detvalue3
               + detvalue4
               + detvalue5
               + detvalue6
               + str1 + str2 + str3 + str4
               + "<a href='/CWS/pages/work/equipmentlist.faces?sewageid=" + id + "'>" + "<font face='Arial'>设备运行记录</font></a>" + "<br/>"
               + "<a href='/CWS/pages/work/detectionlist.faces?sewageid=" + id + "'>" + "<font face='Arial'>数据检测记录</font></a>";
    }
    else
    {
        html = "<b><font face='Arial'>" + sewage.getName() + "</font></b><br/>"
               + eqpstate1
               + eqpstate2
               + eqpstate3
               + detvalue1
               + detvalue2
               + detvalue3
               + detvalue4
               + detvalue5
               + detvalue6
               + str1 + str2 + str3 + str4
               + "<a href='/CWS/pages/work/equipmentlist.faces?sewageid=" + id + "'><font face='Arial'>设备运行记录</font></a>" + "<br/>"
               + "<a href='/CWS/pages/work/detectionlist.faces?sewageid=" + id + "'><font face='Arial'>数据检测记录</font></a>";
    }*/

	/*		if(ru != null){
	if(ru.getEquipment1state() == 3){
		sb.append(getWapper(getRedWapper(se.getEquipment1Name()+"：故障")));
	}else {
		sb.append(getWapper(se.getEquipment1Name()+(ru.getEquipment1state() == 1?"：运行":"：停止")));
	}
	if(ru.getEquipment2state()== 3){
		sb.append(getWapper(getRedWapper(se.getEquipment2Name()+"：故障")));
	}else {
		sb.append(getWapper(se.getEquipment2Name()+(ru.getEquipment2state()== 1?"：运行":"：停止")));
	}
	if(ru.getEquipment3state()== 3){
		sb.append(getWapper(getRedWapper(se.getEquipment3Name()+"：故障")));
	}else {
		sb.append(getWapper(se.getEquipment3Name()+(ru.getEquipment3state()== 1?"：运行":"：停止")));
	}
	if(se.getControlmethod().equals("2")){
		if(se.getEquipment4state().equals("3")){
			sb.append(getWapper(getRedWapper(se.getEquipment4Name()+"：故障")));
		}else {
			sb.append(getWapper(se.getEquipment4Name()+(ru.getEquipment4state()== 1?"：运行":"：停止")));
		}
	}
}*/

/*	if(de != null){
		if(de.getDetection1() > se.getDetection1ul() || de.getDetection1() < se.getDetection1dl()){
			sb.append(getWapper(getOrangeWapper("温度："+new java.text.DecimalFormat("0.00").format(de.getDetection1()))));
		}else{
			sb.append(getWapper(("温度："+new java.text.DecimalFormat("0.00").format(de.getDetection1()))));
		}
		if(de.getDetection2() > se.getDetection2ul() || de.getDetection2() < se.getDetection2dl()){
			sb.append(getWapper(getOrangeWapper("PH："+new java.text.DecimalFormat("0.00").format(de.getDetection2()))));	
		}else {
			sb.append(getWapper(("PH："+new java.text.DecimalFormat("0.00").format(se.getDetection2()))));
		}
		if(de.getDetection3() > se.getDetection3ul() || de.getDetection3() < se.getDetection3dl()){
			sb.append(getWapper(getOrangeWapper("ORP："+new java.text.DecimalFormat("0.00").format(de.getDetection3()))));
		}else {
			sb.append(getWapper(("ORP："+new java.text.DecimalFormat("0.00").format(de.getDetection3()))));
		}
		
		if(de.getDetection4() == 1){
			sb.append(getWapper(getOrangeWapper("LS：高")));
		}else if (de.getDetection4() == 0) {
			sb.append(getWapper(("LS：低")));
		}else {
			sb.append(getWapper(getOrangeWapper("LS：异常")));
		}
		if(de.getDetection5() > se.getDetection5ul() || de.getDetection5() < se.getDetection5dl()){
			sb.append(getWapper(getOrangeWapper("DO："+new java.text.DecimalFormat("0.00").format(de.getDetection5()))));
		}else{
			sb.append(getWapper(("DO："+new java.text.DecimalFormat("0.00").format(de.getDetection5()))));
		}
		if(de.getDetection6() > se.getDetection6ul() || de.getDetection6() < se.getDetection6dl()){
			sb.append(getWapper(getOrangeWapper("流量："+new java.text.DecimalFormat("0.00").format(de.getDetection6()))));
		}else{
			sb.append(getWapper(("流量："+new java.text.DecimalFormat("0.00").format(de.getDetection6()))));
		}
	}*/


}
