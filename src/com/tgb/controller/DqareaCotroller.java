package com.tgb.controller;

import java.io.UnsupportedEncodingException;
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

import com.tgb.entity.Dqarea;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Region;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;
import com.tgb.entity.User;
import com.tgb.service.DqareaManager;
import com.tgb.service.MenuItemManager;
import com.tgb.service.RoleManager;
import com.tgb.service.StaffManager;
import com.tgb.service.StandardManager;
import com.tgb.service.UserManager;
import com.tgb.utils.MD5Util;

@Controller
@RequestMapping("/dqarea")
public class DqareaCotroller {
	@Autowired
	private DqareaManager dqareaManager;
	
	@RequestMapping("/getDqareaList")
	@ResponseBody
	@Transactional
	public Map<String, Object> getDqareaList(int page, int rows){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Dqarea> dqareaList=dqareaManager.findAllByPage(page,rows);
		Long total=dqareaManager.getCount();
		map.put("rows", dqareaList);
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
	public String update( String did,String id,String dqName, String staff,String subarea,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Dqarea dqarea=new Dqarea();
		dqarea.setDqName(dqName);
		dqarea.setStaff(staff);
		dqarea.setSubarea(subarea);
		dqarea.setDid(did);
		dqarea.setId(id);
		dqareaManager.updateDqarea(dqarea);
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
	public String add( String did,String dqName, String staff,String subarea,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Dqarea dqarea=new Dqarea();
		dqarea.setDqName(dqName);
		dqarea.setStaff(staff);
		dqarea.setSubarea(subarea);
		dqarea.setDid(did);
		dqarea.setCreateTime(new Timestamp(new Date().getTime()));
		dqareaManager.addDqarea(dqarea);
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
		dqareaManager.delDqarea(id);
		map.put("success", true);
		//map.put("errorMsg", "删除失败");
		return map;
	}
	
	/**
	 * 查询
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/queryDqareaList")
	@ResponseBody
	@Transactional
	public Map<String, Object> queryDqareaList(int page, int rows,String dqName,String did,String qStarttime,String qEndtime){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			dqName = new String(dqName.getBytes("iso8859-1"),"utf-8");
			did = new String(did.getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Dqarea> dqareaList=dqareaManager.queryByPage(page,rows,dqName,did,qStarttime,qEndtime);
		Long total=dqareaManager.queryCount(dqName,did,qStarttime,qEndtime);
		map.put("rows", dqareaList);
		map.put("total", total);
		return map;
	}
	@RequestMapping("/index")
	public String index(){
		return "/jsp/base/dqarea";
	}
	
}
