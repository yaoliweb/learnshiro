package com.yaoli.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ProduceTestData {
	
	
	public static void main(String[] args) {
/*		for (int i = 1; i <= 14; i++) {
			System.out.println("+getAbnormalData()+\",\"+");
		}*/
/*        Date randomDate = randomDate("2014-06-01", "2014-06-02");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");*/
        //System.out.println(randomDate.toString());
/*        for (int i = 0; i < 200; i++) {
            Date randomDate = randomDate("2016-03-13", "2016-03-16");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	System.out.println(format.format(randomDate));
		}*/
/*		for (int i = 1; i <= 21; i++) {
			System.out.print("equipment"+i+"state,");
		}*/
		ProduceTestData ptd = new ProduceTestData();
		
		List<Date> dates = new ArrayList<Date>();
		
		for (int i = 0; i < 240; i++) {
			Date randomDate = randomDate("2016-03-21", "2016-03-22");
			dates.add(randomDate);
		}
		Collections.sort(dates);
		//每小时随机生成20 条数据，并且按照顺序从小到大排好
		for (int i = 0; i <240; i++) {
			int sewageid = 497;
			boolean isNormal = ptd.getProbilityForNormal(); 
	        //Date randomDate = randomDate("2016-03-15 00:00:00", "2016-03-16 59:59:59");
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        System.out.println("--"+isNormal+"--"+format.format(dates.get(i)));
			System.out.println(ptd.getDetection(sewageid, isNormal, format.format(dates.get(i))));
			System.out.println(ptd.getEquipmenSql(sewageid, isNormal, format.format(dates.get(i))));
		}
	}
	/**
	 * 获取异常的概率
	 * @return 
	 * true 表示这条数据是正常的，
	 * false 表示这条数据是异常的。
	 */
	public boolean getProbilityForNormal(){
		double probility = Math.random();
		if(probility > 0.3){
			return true;
		}
		return false;
	}
	
	public double getNormalData(){
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");  
		Random random = new Random();
		double i = random.nextInt(1000)+Double.valueOf(df.format(Math.random()/10));
		
		Random random2 = new Random();
		int j = random2.nextInt(10);
		if(j%2 == 0){
			i = i * -1;
		}
		return i;
	}
	
	public double getAbnormalData(){
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");  
		Random random = new Random();
		double i = random.nextInt(1000)+1001+Double.valueOf(df.format(Math.random()/10));
		
		Random random2 = new Random();
		int j = random2.nextInt(10);
		if(j%2 == 0){
			i = i * -1;
		}
		return i;
	}
	
	public int getEquipmentAbnormalData(){
		Random random = new Random();
		int i = random.nextInt(2)+1;
		
		Random random2 = new Random();
		int j = random2.nextInt(10);
		if(j%2 == 0){
			i = 3;
		}
		return i;
	}
	
	public int getEquipmentNormalData(){
		Random random = new Random();
		int i = random.nextInt(2)+1;
		return i;
	}
	
	/**
	 * 得到设备的sql
	 * @param isNormal
	 * @return
	 */
	public String getEquipmenSql(int sewageid,boolean isNormal,String testingtime){
		if(isNormal == true){
			String sql = "insert into run_data(sewageid,testingtime,equipment1state,equipment2state,equipment3state,equipment4state,equipment5state,equipment6state,equipment7state,equipment8state,equipment9state,equipment10state,equipment11state,equipment12state,equipment13state,equipment14state,equipment15state,equipment16state,equipment17state,equipment18state,equipment19state,equipment20state,equipment21state)"+
					"values ("
					+sewageid+","
					+"'"+testingtime+"',"
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+","+
					+getEquipmentNormalData()+
					")";
			return sql;
		}else {
			String sql = "insert into run_data(sewageid,testingtime,equipment1state,equipment2state,equipment3state,equipment4state,equipment5state,equipment6state,equipment7state,equipment8state,equipment9state,equipment10state,equipment11state,equipment12state,equipment13state,equipment14state,equipment15state,equipment16state,equipment17state,equipment18state,equipment19state,equipment20state,equipment21state)"+
					"values ("
					+sewageid+","
					+"'"+testingtime+"',"
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+","+
					+getEquipmentAbnormalData()+
					")";
			return sql;
		}
	}
	
	public String getDetection(int sewageid,boolean isNormal,String testingtime){
		if(isNormal == true){
			String sql = "insert into detection_data(sewageid,testingtime,detection1,detection2,detection3,detection4,detection5,detection6,detection7,detection8,detection9,detection10,detection11,detection12,detection13,detection14)"+
					"values("
					+sewageid+","
					+"'"+testingtime+"',"+
					+getNormalData()+","+
					+getNormalData()+","+
					+getNormalData()+","+
					+getNormalData()+","+
					+getNormalData()+","+
					+getNormalData()+","+
					+getNormalData()+","+
					+getNormalData()+","+
					+getNormalData()+","+
					+getNormalData()+","+
					+getNormalData()+","+
					+getNormalData()+","+
					+getNormalData()+","+
					+getNormalData()+
					")";
			return sql;
		}else {
			String sql = "insert into detection_data(sewageid,testingtime,detection1,detection2,detection3,detection4,detection5,detection6,detection7,detection8,detection9,detection10,detection11,detection12,detection13,detection14)"+
					"values("
					+sewageid+","
					+"'"+testingtime+"',"+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+","+
					+getAbnormalData()+
					")";
			return sql;
		}
	}
	
    private static Date randomDate(String beginDate, String endDate) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date start = format.parse(beginDate);// 开始日期
            Date end = format.parse(endDate);// 结束日期
            if (start.getTime() >= end.getTime()) {
                return null;
            }
            long date = random(start.getTime(), end.getTime());
 
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    private static long random(long begin, long end) {
        long rtnn = begin + (long) (Math.random() * (end - begin));
        if (rtnn == begin || rtnn == end) {
            return random(begin, end);
        }
        return rtnn;
    }

}
