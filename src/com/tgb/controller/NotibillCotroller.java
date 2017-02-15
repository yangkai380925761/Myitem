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

import com.tgb.entity.Dqarea;
import com.tgb.entity.MenuItem;
import com.tgb.entity.NoBill;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;
import com.tgb.entity.User;
import com.tgb.entity.WorkBill;
import com.tgb.service.DqareaManager;
import com.tgb.service.MenuItemManager;
import com.tgb.service.NotibillManager;
import com.tgb.service.RoleManager;
import com.tgb.service.StaffManager;
import com.tgb.service.StandardManager;
import com.tgb.service.UserManager;
import com.tgb.utils.MD5Util;

@Controller
@RequestMapping("/notibill")
public class NotibillCotroller {
	@Autowired
	private NotibillManager notibillManager;
	
	@RequestMapping("/getNotibillList")
	@ResponseBody
	@Transactional
	public Map<String, Object> getDqareaList(int page, int rows){
		Map<String, Object> map=new HashMap<String, Object>();
		List<NoBill> noBillList=notibillManager.findAllByPage(page,rows);
		Long total=notibillManager.getCount();
		map.put("rows", noBillList);
		map.put("total", total);
		return map;
	}
	
	/*@RequestMapping("/ajaxList")
	@ResponseBody
	@Transactional
	public Map<String, Object> ajaxList(){
		Map<String, Object> map=new HashMap<String, Object>();
		List<NoBill> standardList=notibillManager.findAll();
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
	public String update(String customerId, String id, String customerName, String linkman,String linkNum,String pickaddress,String arrivecity,String num,String product,String pickdate,String weight,String volume,String remark,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		NoBill noBill=new NoBill();
		noBill.setCustomerName(customerName);
		noBill.setLinkman(linkman);
		noBill.setLinkNum(linkNum);
		noBill.setPickaddress(pickaddress);
		noBill.setArrivecity(arrivecity);
		noBill.setNum(num);
		noBill.setProduct(product);
		noBill.setPickdate(pickdate);
		noBill.setWeight(weight);
		noBill.setVolume(volume);
		noBill.setRemark(remark);
		noBill.setCustomerId(customerId);
		noBill.setId(id);
		notibillManager.updateNoBill(noBill);
		map.put("success", true);
		return "";
	}
	/*
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
	public String add( String customerId,String customerName, String linkman,String linkNum,String pickaddress,String arrivecity,String num,String product,String pickdate,String weight,String volume,String remark,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		NoBill noBill=new NoBill();
		noBill.setCustomerName(customerName);
		noBill.setLinkman(linkman);
		noBill.setLinkNum(linkNum);
		noBill.setPickaddress(pickaddress);
		noBill.setArrivecity(arrivecity);
		noBill.setNum(num);
		noBill.setProduct(product);
		noBill.setPickdate(pickdate);
		noBill.setWeight(weight);
		noBill.setVolume(volume);
		noBill.setRemark(remark);
		noBill.setCustomerId(customerId);
		noBill.setOrdertype("自动");
		noBill.setCreateTime(new Timestamp(new Date().getTime()));
		notibillManager.addNoBill(noBill);
		//WorkBill workBill =new WorkBill();
		
		//map.put("success", true);
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
		notibillManager.delNoBill(id);
		map.put("success", true);
		//map.put("errorMsg", "删除失败");
		return map;
	}
	@RequestMapping("/index")
	public String index(){
		return "/jsp/shouli/noticebill";
	}
	
}
