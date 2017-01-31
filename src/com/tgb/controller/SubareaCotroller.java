package com.tgb.controller;

import java.sql.Timestamp;
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
	/*
	@RequestMapping("/ajaxList")
	@ResponseBody
	@Transactional
	public Map<String, Object> ajaxList(){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Standard> standardList=standardManager.findAll();
		map.put("standardList", standardList);
		return map;
	}
	*/
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
	public String update( String standardName, double minweight,double maxweight,String id,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Standard standard=new Standard();
		standard.setStandardName(standardName);
		standard.setMinweight(minweight);
		standard.setMaxweight(maxweight);
		standard.setId(id);
		//staffManager.updateStandard(standard);
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
	public String add( String standardName, double minweight,double maxweight,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Standard standard=new Standard();
		standard.setStandardName(standardName);
		standard.setMinweight(minweight);
		standard.setMaxweight(maxweight);
		standard.setCreateTime(new Timestamp(new Date().getTime()));
		standard.setCreateBy((String)session.getAttribute("userNameNum"));
		//standardManager.addStandard(standard);
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
		//standardManager.delStandard(id);
		map.put("success", true);
		//map.put("errorMsg", "删除失败");
		return map;
	}
	@RequestMapping("/index")
	public String index(){
		return "/jsp/base/subarea";
	}
	
}
