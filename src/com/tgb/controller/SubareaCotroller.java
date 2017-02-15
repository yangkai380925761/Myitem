package com.tgb.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;



import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.tgb.entity.MenuItem;
import com.tgb.entity.Region;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;
import com.tgb.entity.Subarea;
import com.tgb.entity.User;
import com.tgb.service.MenuItemManager;
import com.tgb.service.RoleManager;
import com.tgb.service.StaffManager;
import com.tgb.service.StandardManager;
import com.tgb.service.SubareaManager;
import com.tgb.service.UserManager;
import com.tgb.utils.MD5Util;

@Controller
@RequestMapping("/subarea")
public class SubareaCotroller {
	@Autowired
	private SubareaManager subareaManager;
	
	@RequestMapping("/getSubareaList")
	@ResponseBody
	@Transactional
	public Map<String, Object> getSubareaList(int page, int rows){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Subarea> subareaList=subareaManager.findAllByPage(page,rows);
		Long total=subareaManager.getCount();
		map.put("rows", subareaList);
		map.put("total", total);
		return map;
	}
	
	@RequestMapping("/ajaxList")
	@ResponseBody
	@Transactional
	public Map<String, Object> ajaxList(){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Subarea> subareaList=subareaManager.findAll();
		map.put("subareaList", subareaList);
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
	public String update( String fid,String id,String region,String addressName, String startNum,String endNum,String hasSingle,String position,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Subarea subarea=new Subarea();
		subarea.setAddressName(addressName);
		subarea.setRegion(region);
		subarea.setStartNum(startNum);
		subarea.setEndNum(endNum);
		subarea.setHasSingle(hasSingle);
		subarea.setPosition(position);
		subarea.setFid(fid);
		subarea.setCreateTime(new Timestamp(new Date().getTime()));
		subarea.setId(id);
		subareaManager.updateSubarea(subarea);
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
	public String add( String fid,String region,String addressName, String startNum,String endNum,String hasSingle,String position,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Subarea subarea=new Subarea();
		subarea.setAddressName(addressName);
		subarea.setRegion(region);
		subarea.setStartNum(startNum);
		subarea.setEndNum(endNum);
		subarea.setHasSingle(hasSingle);
		subarea.setPosition(position);
		subarea.setFid(fid);
		subarea.setCreateTime(new Timestamp(new Date().getTime()));
		subareaManager.addSubuarea(subarea);
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
		subareaManager.delSubarea(id);
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
						subareaManager.addSubuarea(subarea);
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
	
	/**
     * 导出表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportExcel.action", method = RequestMethod.GET)  
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) { 
    	subareaManager.exportExcel(request, response);  
    }
    
    /**
	 * 查询
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/querySubareaList")
	@ResponseBody
	@Transactional
	public Map<String, Object> querySubareaList(int page, int rows,String fid,String addressName,String startNum,String endNum,String position,String hasSingle,String qStarttime,String qEndtime){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			fid = new String(fid.getBytes("iso8859-1"),"utf-8");
			addressName = new String(addressName.getBytes("iso8859-1"),"utf-8");
			//startNum = new String(startNum.getBytes("iso8859-1"),"utf-8");
			//endNum = new String(endNum.getBytes("iso8859-1"),"utf-8");
			position = new String(position.getBytes("iso8859-1"),"utf-8");
			hasSingle = new String(hasSingle.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Subarea> subareaList=subareaManager.queryByPage(page,rows,fid,addressName,startNum,endNum,position,hasSingle,qStarttime,qEndtime);
		Long total=subareaManager.queryCount(fid,addressName,startNum,endNum,position,hasSingle,qStarttime,qEndtime);
		map.put("rows", subareaList);
		map.put("total", total);
		return map;
	}
	
	@RequestMapping("/index")
	public String index(){
		return "/jsp/base/subarea";
	}
	
}
