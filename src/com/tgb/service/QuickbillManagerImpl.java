package com.tgb.service;

import java.awt.Menu;



import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.jdt.internal.compiler.lookup.ClassScope;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgb.Myitem.service.impl.IDao;
import com.tgb.entity.Function;
import com.tgb.entity.MenuItem;
import com.tgb.entity.NoBill;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;
import com.tgb.entity.Subarea;
import com.tgb.entity.WorkBill;
import com.tgb.entity.WorkOrderManage;
import com.tgb.utils.ExportExcelUtil;

@Service
@Transactional
public class QuickbillManagerImpl implements QuickbillManager{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Autowired
	private IDao<WorkOrderManage> iDao;
	@Override
	public List<WorkOrderManage> findAllByPage(int page, int rows) {
		String hql="from WorkOrderManage";
		List<WorkOrderManage> list=iDao.findByPage(hql,page,rows);
		return list;
	}
	@Override
	public Long getCount() {
		String hql="select count(*) from WorkOrderManage";
		Long  count=iDao.count(hql);
		return count;
	}
	/*@Override
	public void updateWorkBill(WorkBill workBill) {
		String hql="update Standard u set u.standardName = ?,u.minweight=?,u.maxweight=? where u.id = ?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, standard.getStandardName());
		query.setDouble(1, standard.getMinweight());
		query.setDouble(2, standard.getMaxweight());
		query.setString(3, standard.getId());
		query.executeUpdate();
		iDao.update(workBill);
	}*/
	@Override
	public void delWorkBill(String id) {
		/*String hql="delete  Standard s where s.id=?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();*/
		WorkBill workbill=new WorkBill();
		Class Workbill=workbill.getClass();
		iDao.deleteById(Workbill, id);
		
	}
	@Override
	public void addWorkOrderManage(WorkOrderManage workOrderManage) {
		/*iDao.getSession().save(standard);
		Transaction tran=iDao.getSession().beginTransaction(); 
		iDao.getSession().flush();
		tran.commit();*/
		iDao.add(workOrderManage);
			
		
	}
	@Override
	public List<WorkOrderManage> findAll() {
		return iDao.findAll("from WorkOrderManage");
	}
	
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String hql="from WorkOrderManage where id=?";
		OutputStream os = null;  
		Workbook wb = null;    //工作薄
	
	try {
		List<Map<String, String>> lo = new ArrayList<Map<String, String>>();
		String Nums = request.getParameter("Nums");
		String[] array1 = Nums.split(",");
		//List<String> list=null;
		/*for(String a:array1){
			list.add(a);
		}*/
    	if(array1 != null){
    		for(int i=0; i<array1.length; i++){
    			List<WorkOrderManage> list = iDao.findByids(hql,array1[i]);
    			if(null != list && list.size()>0){
    				WorkOrderManage workOrderManage = list.get(0);
        			Map<String, String> obj = getAll(workOrderManage);
    	    		lo.add(obj);
        		}
	    	}
    	}
		
    	
		
		//导出Excel文件数据 
    	ExportExcelUtil util = new ExportExcelUtil();
		File file =util.getExcelDemoFile("/template/业务通知表.xlsx");
		
		String sheetName="业务通知表";
		
		wb = util.writeNewWorkBillExcel(file, sheetName, lo);
		
		String fileName="业务通知表_" + sdf.format(new Date()) + ".xlsx"; 
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
		os = response.getOutputStream();
		wb.write(os);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally{
			try {
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		
	}
	
	 //导出任务工具
    public Map<String, String> getAll(WorkOrderManage workOrderManage){
    	Map<String, String> obj = new HashMap<String, String>();
    	obj.put("id", workOrderManage.getId());  //工作单编号
    	obj.put("arrivecity", workOrderManage.getArrivecity());     //起始号
    	obj.put("num", workOrderManage.getNum());//结束号
    	obj.put("weight", workOrderManage.getWeight());//位置信息
    	obj.put("floadreqr", workOrderManage.getFloadreqr());//关联区域
    	obj.put("prodtimelimit", workOrderManage.getProdtimelimit());//关联区域
    	obj.put("prodtype", workOrderManage.getProdtype());//关联区域
    	obj.put("product", workOrderManage.getProduct());//关联区域
    	obj.put("sendername", workOrderManage.getSendername());//关联区域
    	obj.put("senderphone", workOrderManage.getSenderphone());//关联区域
    	obj.put("senderaddr", workOrderManage.getSenderaddr());//关联区域
    	obj.put("receivername", workOrderManage.getReceivername());//关联区域
    	obj.put("receiverphone", workOrderManage.getReceiverphone());//关联区域
    	obj.put("receiveraddr", workOrderManage.getReceiveraddr());//关联区域
    	obj.put("actlweit", workOrderManage.getActlweit());//关联区域
    	obj.put("vol", workOrderManage.getVol());//关联区域
    	/*String i = workOrderManage.getHasSingle(); //单双号
    	if("1".equals(i)){
    		obj.put("hasSingle", "单双号"); 
    	}else if("2".equals(i)){
    		obj.put("hasSingle", "单号"); 
    	}else{
    		obj.put("hasSingle", "双号"); 
    	}*/
    	return obj;
    }
	


	


	
	
	
}
