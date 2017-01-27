package com.tgb.controller;

import java.util.HashMap;



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
	public Map<String, Object> getRoleList(){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Role> roleList=roleManager.findAll();
		map.put("rows", roleList);
		return map;
	}
	@RequestMapping("/index")
	public String getAllUser(Map<String, Object> map){
		return "/jsp/user/role";
	}
	
}
