package com.yaoli.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.yaoli.common.CustomPropertyConfigurer;
import com.yaoli.vo.StatisticDayVO;

public class ExcelView extends AbstractExcelView {

	 protected void buildExcelDocument(Map<String, Object> model,  
	            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)  
	            throws Exception {
		@SuppressWarnings("unchecked")
		List<StatisticDayVO> statisticDayVOs = (List<StatisticDayVO>)model.get("statisticDayVOs");
		//int count = (Integer)model.get("count");
		 
		response.setContentType("application/vnd.ms-excel");     
		response.setHeader("Content-disposition", "attachment;filename=\"dayReport.xls\"");  
		HSSFSheet sheet = workbook.createSheet("dayReport");
		
		CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
		//---设置标题
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("污水站点");
		cell.setCellStyle(cellStyle);
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		
		
		Map<String, String> map = CustomPropertyConfigurer.getProperties();
		int rowoffset = 1;
		int titlepoint = 1;
		//设置设备标题
		for (int i = 8; i <= 21; i++) {
			sheet.addMergedRegion(new CellRangeAddress(0,0,rowoffset,rowoffset+1));
			rowoffset = rowoffset + 2;
			HSSFCell titleCell = row.createCell(titlepoint);
			titlepoint = titlepoint + 2;
			titleCell.setCellValue(map.get("equipment"+i+"name"));
			titleCell.setCellStyle(cellStyle);
		}
		
		HSSFRow row1 = sheet.createRow(1);
		int rowoffset2 = 1;
		//设置子标题：设备运行时间 和停止时间
		for (int i = 8; i <= 21; i++) {
			HSSFCell subtitleCell = row1.createCell(rowoffset2);
			subtitleCell.setCellValue("运行时间");
			subtitleCell.setCellStyle(cellStyle);
			rowoffset2 = rowoffset2 + 1;
			HSSFCell subtitleCel2 = row1.createCell(rowoffset2);
			subtitleCel2.setCellValue("停止时间");
			subtitleCel2.setCellStyle(cellStyle);
			rowoffset2 = rowoffset2 + 1;
		}
		
		
		//设置水质标题
		for (int i = 1; i <= 14; i++) {
			sheet.addMergedRegion(new CellRangeAddress(0,0,rowoffset,rowoffset+2));
			rowoffset = rowoffset + 3;
			HSSFCell titleCell = row.createCell(titlepoint);
			titlepoint = titlepoint + 3;
			titleCell.setCellValue(map.get("detection"+i+"name"));
			titleCell.setCellStyle(cellStyle);
		}
		
		//设置子标题：设备运行时间 和停止时间
		for (int i = 1; i <= 14; i++) {
			HSSFCell subtitleCell = row1.createCell(rowoffset2);
			subtitleCell.setCellValue("最大");
			subtitleCell.setCellStyle(cellStyle);
			rowoffset2 = rowoffset2 + 1;
			HSSFCell subtitleCel2 = row1.createCell(rowoffset2);
			subtitleCel2.setCellValue("最小");
			subtitleCel2.setCellStyle(cellStyle);
			rowoffset2 = rowoffset2 + 1;
			HSSFCell subtitleCel3 = row1.createCell(rowoffset2);
			subtitleCel3.setCellValue("平均");
			subtitleCel3.setCellStyle(cellStyle);
			rowoffset2 = rowoffset2 + 1;
		}
		
		//从第三行开始 前面已经有 0,1
		int controlRowIndex = 2;
		
		int controlColIndex = 0;
		for (StatisticDayVO statisticDayVO : statisticDayVOs) {
			HSSFRow dataRow = sheet.createRow(controlRowIndex);
			
			Class<?> statisticDayVOClass = Class.forName("com.yaoli.vo.StatisticDayVO");
			//填充 设备名称
			Method getSewagenameMethod = statisticDayVOClass.getDeclaredMethod("getSewagename");
			String sewagename = getSewagenameMethod.invoke(statisticDayVO).toString();
			HSSFCell datanamecell = dataRow.createCell(controlColIndex);
			datanamecell.setCellValue(sewagename);
			datanamecell.setCellStyle(cellStyle);
			controlColIndex = controlColIndex + 1;
			
			
			
			//填充 设备运行、停止时间
			for (int i = 8; i <= 21; i++) {
				Method method1 = statisticDayVOClass.getDeclaredMethod("getEquip"+i+"normaltime");
				Object normaltimeObject = method1.invoke(statisticDayVO);
				Integer normaltime = (normaltimeObject == null ? 0:Integer.valueOf(normaltimeObject.toString()));
				
				Method method2 = statisticDayVOClass.getDeclaredMethod("getEquip"+i+"abnormaltime");
				Object abnormaltimeObject = method2.invoke(statisticDayVO);
				Integer abnormaltime = (normaltimeObject == null ? 0:Integer.valueOf(abnormaltimeObject.toString()));
				
				HSSFCell datacell = dataRow.createCell(controlColIndex);
				datacell.setCellValue(normaltime);
				datacell.setCellStyle(cellStyle);
				controlColIndex = controlColIndex + 1;
				
				HSSFCell datacel2 = dataRow.createCell(controlColIndex);
				datacel2.setCellValue(abnormaltime);
				datacel2.setCellStyle(cellStyle);
				controlColIndex = controlColIndex + 1;
			}
			
			
			//填充 水质
			for (int i = 1; i <= 14; i++) {
				Method method1 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"max");
				Object maxObject = method1.invoke(statisticDayVO);
				Double max = (maxObject == null ? 0.00:Double.valueOf(maxObject.toString()));
				
				Method method2 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"min");
				Object minObject = method2.invoke(statisticDayVO);
				Double min = (minObject == null ? 0.00:Double.valueOf(minObject.toString()));
				
				Method method3 = statisticDayVOClass.getDeclaredMethod("getDetection"+i+"avg");
				Object avgObject = method3.invoke(statisticDayVO);
				Double avg = (avgObject == null ? 0.00:Double.valueOf(avgObject.toString()));
				
				HSSFCell datacell = dataRow.createCell(controlColIndex);
				datacell.setCellValue(max);
				datacell.setCellStyle(cellStyle);
				controlColIndex = controlColIndex + 1;
				
				HSSFCell datacel2 = dataRow.createCell(controlColIndex);
				datacel2.setCellValue(min);
				datacel2.setCellStyle(cellStyle);
				controlColIndex = controlColIndex + 1;
				
				HSSFCell datacel3 = dataRow.createCell(controlColIndex);
				datacel3.setCellValue(avg);
				datacel3.setCellStyle(cellStyle);
				controlColIndex = controlColIndex + 1;
			}
		}
	 } 
}
