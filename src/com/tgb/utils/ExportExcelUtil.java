package com.tgb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;


public class ExportExcelUtil {
	/** 
	 * 描述：根据文件路径获取项目中的文件 
	 * @param fileDir 文件路径
	 * @return
	 * @throws Exception
	 */
	public  File getExcelDemoFile(String fileDir) throws Exception{
		String classDir = null;
		String fileBaseDir = null;
		File file = null;
		classDir = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		fileBaseDir = classDir.substring(0, classDir.lastIndexOf("classes"));
		System.out.println(fileBaseDir+fileDir);
		file = new File(fileBaseDir+fileDir);
		if(!file.exists()){
			throw new Exception("模板文件不存在！");
		}
		
		return file;
	}
	/**
	 * 描述：设置简单的Cell样式
	 * @return
	 */
	public  CellStyle setSimpleCellStyle(Workbook wb){
		CellStyle cs = wb.createCellStyle();
		/*
		cs.setBorderBottom(CellStyle.BORDER_THIN); //下边框
		cs.setBorderLeft(CellStyle.BORDER_THIN);//左边框
		cs.setBorderTop(CellStyle.BORDER_THIN);//上边框
		cs.setBorderRight(CellStyle.BORDER_THIN);//右边框
*/
		cs.setAlignment(CellStyle.ALIGN_CENTER); // 居中
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
		return cs;
	}
	
	//导出区域表
		public  Workbook writeNewTaskExcel(File file, String sheetName,List<Map<String, String>> lis) throws Exception{
			Workbook wb = null;
			Row row = null; 
			Cell cell = null;
			FileInputStream fis = new FileInputStream(file);
			wb = new ImportExcelUtil().getWorkbook(fis, file.getName());	//获取工作薄
			Sheet sheet = wb.getSheet(sheetName);
			
			//循环插入数据
			int first = sheet.getFirstRowNum()+1;
			CellStyle cs = setSimpleCellStyle(wb);    //Excel单元格样式
			if("区域信息表".equals(sheetName)){
				for (int i = 0; i < lis.size(); i++) {
					row = sheet.createRow(first+i); //创建新的ROW，用于数据插入
					Short s = 23*23;
					row.setRowStyle(cs);
					row.setHeight(s);
					
					//按项目实际需求，在该处将对象数据插入到Excel中
					Map<String, String> vo  = lis.get(i);
					if(null==vo){
						break;
					}
					//Cell赋值开始				
					cell = row.createCell(0);
					cell.setCellValue(i+1);
					cell.setCellStyle(cs);
					
					cell = row.createCell(1);
					cell.setCellValue(vo.get("addressName"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(2);
					cell.setCellValue(vo.get("startNum"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(3);
					cell.setCellValue(vo.get("endNum"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(4);
					cell.setCellValue(vo.get("position"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(5);
					cell.setCellValue(vo.get("region"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(6);
					cell.setCellValue(vo.get("hasSingle"));
					cell.setCellStyle(cs);
					
				}
			}
			return wb;
		}
		public Workbook writeNewWorkBillExcel(File file, String sheetName,
				List<Map<String, String>> lo) throws Exception {
			Workbook wb = null;
			Row row = null; 
			Cell cell = null;
			FileInputStream fis = new FileInputStream(file);
			wb = new ImportExcelUtil().getWorkbook(fis, file.getName());	//获取工作薄
			Sheet sheet = wb.getSheet(sheetName);
			
			//循环插入数据
			int first = sheet.getFirstRowNum()+1;
			CellStyle cs = setSimpleCellStyle(wb);    //Excel单元格样式
			if("业务通知表".equals(sheetName)){
				for (int i = 0; i < lo.size(); i++) {
					row = sheet.createRow(first+i); //创建新的ROW，用于数据插入
					Short s = 23*23;
					row.setRowStyle(cs);
					row.setHeight(s);
					
					//按项目实际需求，在该处将对象数据插入到Excel中
					Map<String, String> vo  = lo.get(i);
					if(null==vo){
						break;
					}
					//Cell赋值开始				
					cell = row.createCell(0);
					cell.setCellValue(i+1);
					cell.setCellStyle(cs);
					
					cell = row.createCell(1);
					cell.setCellValue(vo.get("id"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(2);
					cell.setCellValue(vo.get("arrivecity"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(3);
					cell.setCellValue(vo.get("num"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(4);
					cell.setCellValue(vo.get("weight"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(5);
					cell.setCellValue(vo.get("floadreqr"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(6);
					cell.setCellValue(vo.get("prodtimelimit"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(7);
					cell.setCellValue(vo.get("prodtype"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(8);
					cell.setCellValue(vo.get("product"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(9);
					cell.setCellValue(vo.get("sendername"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(10);
					cell.setCellValue(vo.get("senderphone"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(11);
					cell.setCellValue(vo.get("senderaddr"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(12);
					cell.setCellValue(vo.get("receivername"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(13);
					cell.setCellValue(vo.get("receiverphone"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(14);
					cell.setCellValue(vo.get("receiveraddr"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(15);
					cell.setCellValue(vo.get("actlweit"));
					cell.setCellStyle(cs);
					
					cell = row.createCell(16);
					cell.setCellValue(vo.get("vol"));
					cell.setCellStyle(cs);
					
					
					
				}
			}
			return wb;
		}
}
