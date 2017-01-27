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
import com.tgb.entity.MenuItem;
import com.tgb.entity.User;
import com.tgb.service.MenuItemManager;
import com.tgb.service.UserManager;
import com.tgb.utils.MD5Util;

@Controller
@RequestMapping("/menu")
public class MenuItemCotroller {
	@Autowired
	private MenuItemManager menuItemManager;
	
	@Resource(name="userManager")
	private UserManager userManager;
	
	@RequestMapping("/show")
	@ResponseBody
	@Transactional
	public Map<String, Object> show(){
		Map<String, Object> map=new HashMap<String, Object>();
		List<MenuItem> menuList=menuItemManager.findAll();
		map.put("menuList", menuList);
		return map;
	}
	@RequestMapping("/index")
	public String getAllUser(Map<String, Object> map){
		//List<MenuItem> menuList=menuItemManager.findAll();
		//List<User> userList=userManager.checkPassAndName(,MD5Util.md5(password));
		//map.put("userList", userList);
		return "/jsp/index";
	}
	
}
