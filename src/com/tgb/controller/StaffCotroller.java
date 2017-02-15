package com.tgb.controller;

import java.io.UnsupportedEncodingException;
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

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;
import com.tgb.entity.User;
import com.tgb.service.MenuItemManager;
import com.tgb.service.RoleManager;
import com.tgb.service.StaffManager;
import com.tgb.service.StandardManager;
import com.tgb.service.UserManager;
import com.tgb.utils.MD5Util;

@Controller
@RequestMapping("/staff")
public class StaffCotroller {
	@Autowired
	private StaffManager staffManager;
	
	@RequestMapping("/getStaffList")
	@ResponseBody
	@Transactional
	public Map<String, Object> getStaffList(int page, int rows){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Staff> staffList=staffManager.findAllByPage(page,rows);
		Long total=staffManager.getCount();
		map.put("rows", staffList);
		map.put("total", total);
		return map;
	}
	
	@RequestMapping("/ajaxList")
	@ResponseBody
	@Transactional
	public Map<String, Object> ajaxList(){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Staff> staffList=staffManager.findAll();
		map.put("staffList", staffList);
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
	public String update( String id,String createTime,String staffName,String phone,String haspda,String station,String standard,String sID,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Staff staff=new Staff();
		staff.setStaffName(staffName);
		staff.setStation(station);
		staff.setPhone(phone);
		staff.setHaspda(haspda);
		staff.setStandard(standard);
		staff.setsID(sID);
		staff.setId(id);
		staffManager.updateStaff(staff);
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
	public String add( String staffName,String phone,String haspda,String station,String standard,String sID,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Staff staff=new Staff();
		staff.setStaffName(staffName);
		staff.setStation(station);
		staff.setPhone(phone);
		staff.setHaspda(haspda);
		staff.setStandard(standard);
		staff.setsID(sID);
		staff.setCreateTime(new Timestamp(new Date().getTime()));
		staffManager.addStaff(staff);
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
		staffManager.delStaff(id);
		map.put("success", true);
		//map.put("errorMsg", "删除失败");
		return map;
	}
	/**
	 * 查找信息的方法
	 * @param id 用户的id 
	 * @param session
	 * @return
	 */
	@RequestMapping("/findInfoByName")
	@ResponseBody
	@Transactional
	public Map<String, Object> findInfoByName( String name,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Staff> staff=staffManager.findInfoByName(name);
		map.put("staff", staff);
		return map;
	}
	
	/**
	 * 查询
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/queryStaffList")
	@ResponseBody
	@Transactional
	public Map<String, Object> queryStaffList(int page, int rows,String staffName,String phone,String station,String haspda,String standard,String qStarttime,String qEndtime){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			staffName = new String(staffName.getBytes("iso8859-1"),"utf-8");
			station = new String(station.getBytes("iso8859-1"),"utf-8");
			standard = new String(standard.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Staff> staffList=staffManager.queryByPage(page,rows,staffName,phone,station,haspda,standard,qStarttime,qEndtime);
		Long total=staffManager.queryCount(staffName,phone,station,haspda,standard,qStarttime,qEndtime);
		map.put("rows", staffList);
		map.put("total", total);
		return map;
	}
	
	@RequestMapping("/index")
	public String index(){
		return "/jsp/base/staff";
	}
	
}
