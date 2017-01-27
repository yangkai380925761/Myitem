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
import com.tgb.entity.Role;
import com.tgb.entity.Standard;
import com.tgb.entity.User;
import com.tgb.service.MenuItemManager;
import com.tgb.service.RoleManager;
import com.tgb.service.StandardManager;
import com.tgb.service.UserManager;
import com.tgb.utils.MD5Util;

@Controller
@RequestMapping("/standard")
public class StandardCotroller {
	@Autowired
	private StandardManager standardManager;
	
	@RequestMapping("/getStandardList")
	@ResponseBody
	@Transactional
	public Map<String, Object> getStandardList(int page, int rows){
		Map<String, Object> map=new HashMap<String, Object>();
		List<Standard> standardList=standardManager.findAllByPage(page,rows);
		Long total=standardManager.getCount();
		map.put("rows", standardList);
		map.put("total", total);
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
	public String update( String standardName, double minweight,double maxweight,String id,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		Standard standard=new Standard();
		standard.setStandardName(standardName);
		standard.setMinweight(minweight);
		standard.setMaxweight(maxweight);
		standard.setId(id);
		standardManager.updateStandard(standard);
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
		standardManager.delStandard(id);
		map.put("success", true);
		//map.put("errorMsg", "删除失败");
		return map;
	}
	@RequestMapping("/index")
	public String index(){
		return "/jsp/base/standard";
	}
	
}
