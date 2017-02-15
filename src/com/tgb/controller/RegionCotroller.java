package com.tgb.controller;

import java.io.File;
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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tgb.entity.MenuItem;
import com.tgb.entity.Region;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;
import com.tgb.entity.User;
import com.tgb.service.MenuItemManager;
import com.tgb.service.RegionManager;
import com.tgb.service.RoleManager;
import com.tgb.service.StaffManager;
import com.tgb.service.StandardManager;
import com.tgb.service.UserManager;
import com.tgb.utils.MD5Util;
import com.tgb.utils.PinYin4jUtils;

@Controller
@RequestMapping("/region")
public class RegionCotroller {
	@Autowired
	private RegionManager regionManager;
	
	@RequestMapping("/getRegionList")
	@ResponseBody
	@Transactional
	public Map<String, Object> getRegionList(int page, int rows){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Region> regionList=regionManager.findAllByPage(page,rows);
		Long total=regionManager.getCount();
		map.put("rows", regionList);
		map.put("total", total);
		return map;
	}
	
	@RequestMapping("/ajaxList")
	@ResponseBody
	@Transactional
	public Map<String, Object> ajaxList(){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Region> regionList=regionManager.findAll();
		map.put("regionList", regionList);
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
	public String update(String id, String areaName,String pCode,String cCode,String dCode, String postcode,String shortcode,String citycode,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Region region=new Region();
		region.setPostcode(postcode);
		region.setShortcode(shortcode);
		region.setCitycode(citycode);
		String[] areaNames=areaName.split(" ");
		if(areaNames.length>2){
			region.setProvince(areaNames[0]);
			region.setCity(areaNames[1]);
			region.setDistrict(areaNames[2]);
		}else{
			region.setProvince("直辖市");
			region.setCity(areaNames[0]);
			region.setDistrict(areaNames[1]);
		}
		region.setpCode(pCode);
		region.setcCode(cCode);
		region.setdCode(dCode);
		region.setId(id);
		region.setCreateTime(new Timestamp(new Date().getTime()));
		region.setCreateBy((String)session.getAttribute("userNameNum"));
		regionManager.updateRegion(region);
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
	public String add( String areaName,String pCode,String cCode,String dCode, String postcode,String shortcode,String citycode,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Region region=new Region();
		region.setPostcode(postcode);
		region.setShortcode(shortcode);
		region.setCitycode(citycode);
		String[] areaNames=areaName.split(" ");
		if(areaNames.length>2){
			region.setProvince(areaNames[0]);
			region.setCity(areaNames[1]);
			region.setDistrict(areaNames[2]);
		}else{
			region.setProvince("直辖市");
			region.setCity(areaNames[0]);
			region.setDistrict(areaNames[1]);
		}
		region.setpCode(pCode);
		region.setcCode(cCode);
		region.setdCode(dCode);
		region.setCreateTime(new Timestamp(new Date().getTime()));
		region.setCreateBy((String)session.getAttribute("userNameNum"));
		regionManager.addRegion(region);
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
		regionManager.delRegion(id);
		map.put("success", true);
		//map.put("errorMsg", "删除失败");
		return map;
	}
	
	/**
	 * 批量导入的方法
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
				//POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(files3));
				// 1、 工作薄对象
				// HSSFWorkbook wb = new HSSFWorkbook(fs);
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
						Region region = new Region();
						/*String id =row.getCell(0).getStringCellValue(); // 获得第一个单元格信息
						if (id.trim().equals("")) {
							// id 无值，跳过
							continue;
						}
						
						region.setId(id);*/
						for(int j=0;j<row.getPhysicalNumberOfCells();j++){
							
							switch (row.getCell(j).getCellType()) {
							
							case HSSFCell.CELL_TYPE_STRING:
								
								if(j==0){
									region.setId(row.getCell(j).getStringCellValue());
								}else if(j==2){
									region.setProvince(row.getCell(j).getStringCellValue());
								}else if(j==6){
									region.setCitycode(row.getCell(j).getStringCellValue());
								}else if(j==7){
									region.setShortcode(row.getCell(j).getStringCellValue());
								}else if(j==1){
									region.setPostcode(row.getCell(j).getStringCellValue());
								}
								
								break;
								
							case HSSFCell.CELL_TYPE_FORMULA:
								
								if(j==0){
									region.setId(row.getCell(j).getStringCellValue());
								}else if(j==2){
									region.setProvince(row.getCell(j).getStringCellValue());
								}else if(j==6){
									region.setCitycode(row.getCell(j).getStringCellValue());
								}else if(j==7){
									region.setShortcode(row.getCell(j).getStringCellValue());
								}else if(j==1){
									region.setPostcode(row.getCell(j).getStringCellValue());
								}
								
								break;
								
							case HSSFCell.CELL_TYPE_NUMERIC:
								
								HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
								
								String cellFormatted = dataFormatter.formatCellValue(row.getCell(j));
								
								if(j==0){
									region.setId(cellFormatted);
								}else if(j==2){
									region.setProvince(cellFormatted);
								}else if(j==6){
									region.setCitycode(cellFormatted);
								}else if(j==7){
									region.setShortcode(cellFormatted);
								}else if(j==1){
									region.setPostcode(cellFormatted);
								}        
								break;
								
							case HSSFCell.CELL_TYPE_ERROR:
								
								break;
								
							}
							
							/*region.setProvince(row.getCell(1).getStringCellValue());
							region.setCity(row.getCell(2).getStringCellValue());
							region.setDistrict(row.getCell(3).getStringCellValue());
							region.setPostcode(row.getCell(4).getStringCellValue());*/
						}
						
						/*// 使用pinyin4j 生成简码和城市编码
						// 连接省市区
						String str = region.getProvince() + region.getCity() + region.getDistrict();
						str = str.replaceAll("省", "").replaceAll("市", "").replaceAll("区", "");
						
						// 使用pinyin4j生成简码
						String[] arr = PinYin4jUtils.getHeadByString(str); // [B,J,B,J,G,D]
						StringBuffer sb = new StringBuffer();
						for (String headChar : arr) {
							sb.append(headChar);
						}
						region.setShortcode(sb.toString()); // 简码
*/						
						// 生成城市编码
						//region.setCitycode(PinYin4jUtils.hanziToPinyin(region.getCity(), ""));

					// 保存region信息 (批量导入 如果出错怎么办 ？)
					try {
						regionManager.addRegion(region);
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
    	regionManager.exportExcel(request, response);  
    }
    
    /**
	 * 查询
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/queryRegionList")
	@ResponseBody
	@Transactional
	public Map<String, Object> queryRegionList(int page, int rows,String province,String city,String district,String shortcode,String citycode,String postcode,String qStarttime,String qEndtime){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			shortcode = new String(shortcode.getBytes("iso8859-1"),"utf-8");
			citycode = new String(citycode.getBytes("iso8859-1"),"utf-8");
			postcode = new String(postcode.getBytes("iso8859-1"),"utf-8");
			province = new String(province.getBytes("iso8859-1"),"utf-8");
			city = new String(city.getBytes("iso8859-1"),"utf-8");
			district = new String(district.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Region> regionList=regionManager.queryByPage(page,rows,province,city,district,shortcode,citycode,postcode,qStarttime,qEndtime);
		Long total=regionManager.queryCount(province,city,district,shortcode,citycode,postcode,qStarttime,qEndtime);
		map.put("rows", regionList);
		map.put("total", total);
		return map;
	}
	@RequestMapping("/index")
	public String index(){
		return "/jsp/base/region";
	}
	
	
}
