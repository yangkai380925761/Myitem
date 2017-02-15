package com.tgb.service;

import java.awt.Menu;



import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.sql.Timestamp;
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
import com.tgb.entity.MenuItem;
import com.tgb.entity.Region;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;
import com.tgb.entity.Subarea;
import com.tgb.utils.ExportExcelUtil;

@Service
@Transactional
public class SubareaManagerImpl implements SubareaManager{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Autowired
	private IDao<Subarea> iDao;
	@Override
	public List<Subarea> findAllByPage(int page, int rows) {
		String hql="from Subarea";
		List<Subarea> list=iDao.findByPage(hql,page,rows);
		return list;
	}
	@Override
	public Long getCount() {
		String hql="select count(*) from Subarea";
		Long  count=iDao.count(hql);
		return count;
	}
	@Override
	public void updateSubarea(Subarea subarea) {
		/*String hql="update Standard u set u.standardName = ?,u.minweight=?,u.maxweight=? where u.id = ?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, standard.getStandardName());
		query.setDouble(1, standard.getMinweight());
		query.setDouble(2, standard.getMaxweight());
		query.setString(3, standard.getId());
		query.executeUpdate();*/
		iDao.update(subarea);
	}
	@Override
	public void delSubarea(String id) {
		/*String hql="delete  Standard s where s.id=?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();*/
		Subarea subarea=new Subarea();
		Class Subarea=subarea.getClass();
		iDao.deleteById(Subarea, id);
		
	}
	@Override
	public void addSubuarea(Subarea subarea) {
		/*iDao.getSession().save(standard);
		Transaction tran=iDao.getSession().beginTransaction(); 
		iDao.getSession().flush();
		tran.commit();*/
		iDao.add(subarea);
			
		
	}
	@Override
	public List<Subarea> findAll() {
		return iDao.findAll("from Subarea");
	}
	@Override
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String hql="from Subarea where id=?";
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
    			List<Subarea> list = iDao.findByids(hql,array1[i]);
    			if(null != list && list.size()>0){
    				Subarea subarea = list.get(0);
        			Map<String, String> obj = getAll(subarea);
    	    		lo.add(obj);
        		}
	    	}
    	}
		
    	
		
		//导出Excel文件数据 
    	ExportExcelUtil util = new ExportExcelUtil();
		File file =util.getExcelDemoFile("/template/区域信息表.xlsx");
		
		String sheetName="区域信息表";
		
		wb = util.writeNewTaskExcel(file, sheetName, lo);
		
		String fileName="区域信息表_" + sdf.format(new Date()) + ".xlsx"; 
		
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
    public Map<String, String> getAll(Subarea subarea){
    	Map<String, String> obj = new HashMap<String, String>();
    	obj.put("addressName", subarea.getAddressName());  //关键字
    	obj.put("startNum", subarea.getStartNum());     //起始号
    	obj.put("endNum", subarea.getEndNum());//结束号
    	obj.put("position", subarea.getPosition());//位置信息
    	obj.put("region", subarea.getRegion());//关联区域
    	String i = subarea.getHasSingle(); //单双号
    	if("1".equals(i)){
    		obj.put("hasSingle", "单双号"); 
    	}else if("2".equals(i)){
    		obj.put("hasSingle", "单号"); 
    	}else{
    		obj.put("hasSingle", "双号"); 
    	}
    	return obj;
    }
	@Override
	public List<Subarea> queryByPage(int page, int rows, String fid,
			String addressName, String startNum, String endNum,
			String position, String hasSingle, String qStarttime,
			String qEndtime) {
		String sql="FROM Subarea WHERE 1=1";
		
		if(fid!=null&&!fid.equals("")&&!fid.equals(" ")){
			sql += " and fid like '%" + fid + "%'";
		}
		if(addressName!=null&&!addressName.equals("")&&!addressName.equals(" ")){
			sql +=" and addressName like '%" + addressName + "%'";
		}
	    if(startNum != null && !startNum.equals("")&& !startNum.equals(" ")){
	    	sql += " and startNum >= '" + startNum + "'";
	    }
	    if(endNum != null && !endNum.equals("")&& !endNum.equals(" ")){
	    	sql += " and endNum <= '" + endNum + "'";
	    }
	    if(position != null && !position.equals("")&& !position.equals(" ")){
	    	sql += " and position like '%" + position + "%'";
	    }
	    if(hasSingle != null && !hasSingle.equals("")&& !hasSingle.equals(" ")){
	    	sql += " and hasSingle like '%" + hasSingle + "%'";
	    }
	    if (qStarttime != null && qStarttime != "") {
	    	StringBuffer sb=new StringBuffer(qStarttime);
	    	String st=sb.append(" 00:00:00").toString();
			Timestamp starttime = Timestamp.valueOf(st);
			sql += " and createTime >= '" + starttime + "'";
		}
		if (qEndtime != null && qEndtime != "") {
			StringBuffer sb=new StringBuffer(qEndtime);
	    	String end=sb.append(" 00:00:00").toString();
			Timestamp endtime = Timestamp.valueOf(end);
			sql += " and createTime <= '" + endtime + "'";
		} 
		List<Subarea> list=iDao.queryByPage(page,rows,sql);
		return list;
	}
	@Override
	public Long queryCount(String fid, String addressName, String startNum,
			String endNum, String position, String hasSingle,
			String qStarttime, String qEndtime) {
		String sql="FROM Subarea WHERE 1=1";
		
		if(fid!=null&&!fid.equals("")&&!fid.equals(" ")){
			sql += " and fid like '%" + fid + "%'";
		}
		if(addressName!=null&&!addressName.equals("")&&!addressName.equals(" ")){
			sql +=" and addressName like '%" + addressName + "%'";
		}
	    if(startNum != null && !startNum.equals("")&& !startNum.equals(" ")){
	    	sql += " and startNum >= '" + startNum + "'";
	    }
	    if(endNum != null && !endNum.equals("")&& !endNum.equals(" ")){
	    	sql +=" and endNum <= '" + endNum + "'";
	    }
	    if(position != null && !position.equals("")&& !position.equals(" ")){
	    	sql += " and position like '%" + position + "%'";
	    }
	    if(hasSingle != null && !hasSingle.equals("")&& !hasSingle.equals(" ")){
	    	sql += " and hasSingle like '%" + hasSingle + "%'";
	    }
	    if (qStarttime != null && qStarttime != "") {
	    	StringBuffer sb=new StringBuffer(qStarttime);
	    	String st=sb.append(" 00:00:00").toString();
			Timestamp starttime = Timestamp.valueOf(st);
			sql += " and createTime >= '" + starttime + "'";
		}
		if (qEndtime != null && qEndtime != "") {
			StringBuffer sb=new StringBuffer(qEndtime);
	    	String end=sb.append(" 00:00:00").toString();
			Timestamp endtime = Timestamp.valueOf(end);
			sql += " and createTime <= '" + endtime + "'";
		} 
		return iDao.count(Subarea.class,sql);
	}
	


	
	
	
}
