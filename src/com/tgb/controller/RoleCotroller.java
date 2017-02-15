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

import com.tgb.entity.Function;
import com.tgb.entity.Role;
import com.tgb.service.RoleManager;

@Controller
@RequestMapping("/role")
public class RoleCotroller {
	@Autowired
	private RoleManager roleManager;
	
	@RequestMapping("/getRoleList")
	@ResponseBody
	@Transactional
	public Map<String, Object> getRoleList(int page, int rows){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Role> functionList=roleManager.findAllByPage(page,rows);
		Long total=roleManager.getCount();
		map.put("rows", functionList);
		map.put("total", total);
		return map;
	}
	
	/*@RequestMapping("/ajaxList")
	@ResponseBody
	@Transactional
	public Map<String, Object> ajaxList(){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Function> functionList=roleManager.findAll();
		map.put("functionList", functionList);
		return map;
	}*/
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
	public String update(String id,String roleName,String description,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Role role=new Role();
		role.setRoleName(roleName);
		role.setDescription(description);
		role.setId(id);
		roleManager.updateRole(role);
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
	public String add( String roleName,String description,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Role role=new Role();
		role.setRoleName(roleName);
		role.setDescription(description);
		//role.setCreateTime(new Timestamp(new Date().getTime()));
		roleManager.addRole(role);
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
		roleManager.delRole(id);
		map.put("success", true);
		//map.put("errorMsg", "删除失败");
		return map;
	}
	
	@RequestMapping("/index")
	public String getAllUser(Map<String, Object> map){
		return "/jsp/user/role";
	}
	
}
