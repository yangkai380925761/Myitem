package com.tgb.controller;

import java.io.IOException;




import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import sun.applet.resources.MsgAppletViewer;


import com.tgb.entity.User;
import com.tgb.service.UserManager;
import com.tgb.utils.MD5Util;

@Controller
@RequestMapping("/user")
public class UserController {
	//用户和Session绑定关系 
	public static final Map<String, HttpSession> USER_SESSION=new HashMap<String, HttpSession>();
	//seeionId和用户的绑定关系 
	public static final Map<String, String> SESSIONID_USER=new HashMap<String, String>();    
	
	@Resource(name="userManager")
	private UserManager userManager;
	/**
	 * 登录
	 * @param userName
	 * @param password
	 * @param request
	 * @param sess
	 * @return
	 */
	@RequestMapping("/login.action")
	@ResponseBody
	@Transactional
	public Map<String, Object> login( String userName, String password,HttpServletRequest request,HttpSession sess){
		User user=new User();
		Map<String, Object> map=new HashMap<String, Object>();
		Connection conn=null;
		user.setUserName(userName);
		user.setPassword(password);
		
		sess.setAttribute("user", user);
	    sess.setAttribute("userNameNum", user.getUserName());
	   // List<User> userInfo=userManager.findByUserName((String)sess.getAttribute("userNameNum"),conn);
		List<User> userList=userManager.checkPassAndName(userName,MD5Util.md5(password));
		
		if(userList.size()==0){
			//request.setAttribute("msg", "用户名或密码错误");
			//map.put("url", "login.jsp");
			map.put("msg", "用户名或密码错误！");
			return map;
		}
	    //添加用户与HttpSession的绑定  
   	 	USER_SESSION.put(userName.trim(), sess);  
   	 	//添加sessionId和用户的绑定  
   	 	SESSIONID_USER.put(sess.getId(), userName);
		map.put("url", "menu/index");
		map.put("userList", userList);
		return map;
	}
	/**
	 * 获取当前的用户
	 * @param sess
	 * @return
	 */
	@RequestMapping("/getCurrentUser.action")
	@ResponseBody
	@Transactional
	public Map<String, Object> getCurrentUser(HttpSession sess){
		Map<String, Object> map=new HashMap<String,Object>();
		List<User> userInfo=userManager.findByUserName(SESSIONID_USER.get(sess.getId()));
		map.put("userInfo", userInfo);
		return map;
		
	}
	/**
	 * 修改密码的方法
	 * @param password
	 * @param sess
	 * @return
	 */
	@RequestMapping("/changePass.action")
	@ResponseBody
	@Transactional
	public Map<String, String> changePass(String password,HttpSession sess){
		Map<String, String> map=new HashMap<String,String>();
		int i=userManager.changePass(password,SESSIONID_USER.get(sess.getId()));
		if(i==0){
			map.put("result","fail");
			map.put("msg", "修改失败！");
		}
		map.put("result","success");
		map.put("msg", "修改成功！");
		return map;
		
	}
	/**
	 * 管理员修改用户密码的方法
	 * @param password
	 * @param sess
	 * @return
	 */
	
	@RequestMapping("/upPass.action")
	@ResponseBody
	@Transactional
	public Map<String, String> upPass(String id,String password){
		Map<String, String> map=new HashMap<String,String>();
		int i=userManager.upPass(id,password);
		if(i==0){
			map.put("msg", "0");
		}
		map.put("msg", "1");
		return map;
		
	}
	@RequestMapping("/findPass.action")
	@ResponseBody
	@Transactional
	public Map<String, String> findPass(String id,String password){
		Map<String, String> map=new HashMap<String,String>();
		int i=userManager.findPass(id,password);
		if(i==0){
			map.put("msg", "0");
		}else{
			map.put("msg", "1");
		}
		
		return map;
		
	}
	/**
	 * 分页获取用户的数据
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/getUserList")
	@ResponseBody
	@Transactional
	public Map<String, Object> getUserList(int page, int rows){
		Map<String, Object> map=new HashMap<String, Object>();
		map=userManager.getUserByPage(page,rows);
		map.put("success", Boolean.valueOf(true));
		return map;
	}
	/**
	 * 添加用户
	 * @param userName
	 * @param password
	 * @param email
	 * @param phone
	 * @param userTrueName
	 * @param userType
	 * @param session
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	@Transactional
	public String add( String userName, String password,String email,String phone,String userTrueName,String userType,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		User user=new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setUserTrueName(userTrueName);
		user.setEmail(email);
		user.setUserType(userType);
		user.setPhone(phone);
		user.setCreateTime(new Timestamp(new Date().getTime()));
		userManager.addUser(user);
		map.put("success", true);
		return "";
	}
	/**
	 * 修改用户的方法
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
	public String update( String userName, String email,String phone,String userTrueName,String userType,String id,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		User user=new User();
		user.setUserName(userName);
		user.setUserTrueName(userTrueName);
		user.setEmail(email);
		user.setUserType(userType);
		user.setPhone(phone);
		user.setId(id);
		userManager.updateUser(user);
		map.put("success", true);
		return "";
	}
	/**
	 * 删除用户的方法
	 * @param id 用户的id 
	 * @param session
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@Transactional
	public Map<String, Object> delete( String id,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		//User user=userManager.findUserById(id);
		userManager.delUser(id);
		map.put("success", true);
		//map.put("errorMsg", "删除失败");
		return map;
	}
	/**
	 * 校验用户是否存在
	 * @param id 用户的id
	 * @param session 用户的 session
	 * @return
	 */
	@RequestMapping("/isExist")
	@ResponseBody
	@Transactional
	public boolean isExist(String id,String userName,HttpSession session){
		boolean flag=false;
		try {
			flag=userManager.findByUserName(id,userName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	//------------------------------跳转到对应模块--------------------------------------
	/**
	 * 跳转到用户管理的界面
	 * @return
	 */
	@RequestMapping("/show")
	public String show(){
		return "/jsp/user/show";
	}
	
	/**
	 * 退出删除session跳转到登录页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/destroySession")
	public String destroySession(HttpServletRequest request){
		request.getSession().invalidate();
		return "/login";
	}
	
	//-----------------------------暂时不用的方法----------------------------
	@RequestMapping("/getAllUser")
	public String getAllUser(HttpServletRequest request){
		
		request.setAttribute("userList", userManager.getAllUser());
		
		return "/index";
	}
	
	@RequestMapping("/getUser")
	public String getUser(String id,HttpServletRequest request){
		
		request.setAttribute("user", userManager.getUser(id));
	
		return "/editUser";
	}
	
	@RequestMapping("/toAddUser")
	public String toAddUser(){
		return "/addUser";
	}
	
	@RequestMapping("/addUser")
	public String addUser(User user,HttpServletRequest request){
		
		userManager.addUser(user);
		
		return "redirect:/user/getAllUser";
	}
	
	@RequestMapping("/delUser")
	public void delUser(String id,HttpServletResponse response){
		
		String result = "{\"result\":\"error\"}";
		
		if(userManager.delUser(id)){
			result = "{\"result\":\"success\"}";
		}
		
		response.setContentType("application/json");
		
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(User user,HttpServletRequest request){
		
		if(userManager.updateUser(user)){
			user = userManager.getUser(user.getId());
			request.setAttribute("user", user);
			return "redirect:/user/getAllUser";
		}else{
			return "/error";
		}
	}
}