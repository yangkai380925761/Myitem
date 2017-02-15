package com.tgb.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;



import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tgb.entity.Function;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;
import com.tgb.entity.Subarea;
import com.tgb.entity.User;
import com.tgb.service.FunctionManager;
import com.tgb.service.MenuItemManager;
import com.tgb.service.RoleManager;
import com.tgb.service.StaffManager;
import com.tgb.service.StandardManager;
import com.tgb.service.UserManager;
import com.tgb.utils.MD5Util;

@Controller
@RequestMapping("/functions")
public class FunctionCotroller {
	@Autowired
	private FunctionManager functionManager;
	
	@RequestMapping("/getFunctionList")
	@ResponseBody
	@Transactional
	public Map<String, Object> getFunctionList(int page, int rows){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Function> functionList=functionManager.findAllByPage(page,rows);
		Long total=functionManager.getCount();
		map.put("rows", functionList);
		map.put("total", total);
		return map;
	}
	
	@RequestMapping("/ajaxList")
	@ResponseBody
	@Transactional
	public Map<String, Object> ajaxList(){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Function> functionList=functionManager.findAll();
		map.put("functionList", functionList);
		return map;
	}
	/**
	 * 修改收派标准的方法
	 * @param userName
	 * @param email
	 * @param phone
	 * @param userTrueName
	 * @param userType
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	@Transactional
	public String update(String id,String functionName,String page,String generateMenu,String zindex,String parentFunction,String description,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Function function=new Function();
		function.setFunctionName(functionName);
		function.setPage(page);
		function.setGenerateMenu(generateMenu);
		function.setZindex(zindex);
		function.setParentFunction(parentFunction);
		function.setDescription(description);
		function.setId(id);
		functionManager.updateFunction(function);
		map.put("success", true);
		return "";
	}
	/**
	 * 增加
	 * @param standardName
	 * @param minweight
	 * @param maxweight
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	@Transactional
	public String add( String functionName,String page,String generateMenu,String zindex,String parentFunction,String description,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Function function=new Function();
		function.setFunctionName(functionName);
		function.setPage(page);
		function.setGenerateMenu(generateMenu);
		function.setZindex(zindex);
		function.setParentFunction(parentFunction);
		function.setDescription(description);
		function.setCreateTime(new Timestamp(new Date().getTime()));
		functionManager.addFunction(function);
		map.put("success", true);
		return "";
	}
	/**
	 * 删除收派标准的方法
	 * @param id 用户的id 
	 * @param session
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@Transactional
	public Map<String, Object> delete( String id,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		functionManager.delFunction(id);
		map.put("success", true);
		//map.put("errorMsg", "删除失败");
		return map;
	}
	/**
	 * 导入的方法
	 * @param id 用户的id 
	 * @param session
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	//@RequestMapping("/importXls")
	@RequestMapping(value = "/importXls.action", method = RequestMethod.POST) 
	@ResponseBody
	@Transactional
	public Map<String, Object> importXls(String file,HttpSession session) throws FileNotFoundException, IOException{
		// 进行Excel解析
				String files=file.replace("\\","/");
				String files2=files.replace("/fakepath","");
				String files3=files2.replace("C","D");
				// 1、 工作薄对象
				HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(files3));
				// 解析工作薄
				hssfWorkbook.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK); // 避免空指针异常

				// 2、 获得Sheet
				 HSSFSheet sheet = hssfWorkbook.getSheetAt(0); // 获得第一个sheet
				 int rowNum=sheet.getLastRowNum();
				
				/* HSSFRow row1 = sheet.getRow(2);
				 HSSFCell cell = row1.getCell((short) 2);  
				 HSSFCellStyle cellStyle = cell.getCellStyle();*/
				 
				// 3、遍历每一行
				for (int i=0;i<rowNum;i++) {
						HSSFRow row = sheet.getRow(i);
						// 进行解析 ， 每一行数据，对应 Region 区域信息
						if (row.getRowNum() == 0) {// 第一行（表头，无需解析）
							continue;
						}
						// 从第二行 开始解析
						Subarea subarea = new Subarea();
						for(int j=0;j<row.getPhysicalNumberOfCells();j++){
							
							switch (row.getCell(j).getCellType()) {
							
							case HSSFCell.CELL_TYPE_STRING:
								
								if(j==0){
									subarea.setId(row.getCell(j).getStringCellValue());
								}else if(j==1){
									subarea.setRegion(row.getCell(j).getStringCellValue());
								}else if(j==2){
									subarea.setAddressName(row.getCell(j).getStringCellValue());
								}else if(j==3){
									subarea.setStartNum(row.getCell(j).getStringCellValue());
								}else if(j==4){
									subarea.setEndNum(row.getCell(j).getStringCellValue());
								}else if(j==5){
									subarea.setHasSingle(row.getCell(j).getStringCellValue());
								}else if(j==6){
									subarea.setPosition(row.getCell(j).getStringCellValue());
								}
								
								break;
								
							case HSSFCell.CELL_TYPE_FORMULA:
								
								if(j==0){
									subarea.setId(row.getCell(j).getStringCellValue());
								}else if(j==1){
									subarea.setRegion(row.getCell(j).getStringCellValue());
								}else if(j==2){
									subarea.setAddressName(row.getCell(j).getStringCellValue());
								}else if(j==3){
									subarea.setStartNum(row.getCell(j).getStringCellValue());
								}else if(j==4){
									subarea.setEndNum(row.getCell(j).getStringCellValue());
								}else if(j==5){
									subarea.setHasSingle(row.getCell(j).getStringCellValue());
								}else if(j==6){
									subarea.setPosition(row.getCell(j).getStringCellValue());
								}
								
								break;
								
							case HSSFCell.CELL_TYPE_NUMERIC:
								
								HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
								
								String cellFormatted = dataFormatter.formatCellValue(row.getCell(j));
								
								if(j==0){
									subarea.setId(cellFormatted);
								}else if(j==1){
									subarea.setRegion(cellFormatted);
								}else if(j==2){
									subarea.setAddressName(cellFormatted);
								}else if(j==3){
									subarea.setStartNum(cellFormatted);
								}else if(j==4){
									subarea.setEndNum(cellFormatted);
								}else if(j==5){
									subarea.setHasSingle(cellFormatted);
								}else if(j==6){
									subarea.setPosition(cellFormatted);
								}
								break;
								
							case HSSFCell.CELL_TYPE_ERROR:
								
								break;
								
							}
							
						}	
					try {
						
					} catch (Exception e) {
						// 导入region失败，记录日志
						//LOG.error("区域导入失败，编号：" + region.getId(), e);
					}
				}

				// 返回json
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("result", "success");
				map.put("msg", "区域导入完成");
				map.put("state", 1);
				return map;
	}
	@RequestMapping("/index")
	public String index(){
		return "/jsp/user/function";
	}
	
}
