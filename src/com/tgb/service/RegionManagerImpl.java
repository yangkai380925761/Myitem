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
import com.tgb.utils.ExportExcelUtil;

@Service
@Transactional
public class RegionManagerImpl implements RegionManager{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Autowired
	private IDao<Region> iDao;
	@Override
	public List<Region> findAllByPage(int page, int rows) {
		String hql="from Region";
		List<Region> list=iDao.findByPage(hql,page,rows);
		return list;
	}
	@Override
	public Long getCount() {
		String hql="select count(*) from Region";
		Long  count=iDao.count(hql);
		return count;
	}
	@Override
	public void updateRegion(Region region) {
		/*String hql="update Standard u set u.standardName = ?,u.minweight=?,u.maxweight=? where u.id = ?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, standard.getStandardName());
		query.setDouble(1, standard.getMinweight());
		query.setDouble(2, standard.getMaxweight());
		query.setString(3, standard.getId());
		query.executeUpdate();*/
		iDao.update(region);
	}
	@Override
	public void delRegion(String id) {
		/*String hql="delete  Standard s where s.id=?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();*/
		Region region=new Region();
		Class Region=region.getClass();
		iDao.deleteById(Region, id);
		
	}
	@Override
	public void addRegion(Region region) {
		/*iDao.getSession().save(standard);
		Transaction tran=iDao.getSession().beginTransaction(); 
		iDao.getSession().flush();
		tran.commit();*/
		iDao.add(region);
			
		
	}
	@Override
	public List<Region> findAll() {
		return iDao.findAll("from Region");
	}
	@Override
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response) {
			OutputStream os = null;  
			Workbook wb = null;    //工作薄
		
		try {
			List<Map<String, String>> lo = new ArrayList<Map<String, String>>();
			String taskNums = request.getParameter("taskNums");
			String[] array1 = taskNums.split(",");
	    	if(array1 != null){
	    		for(int i=0; i<array1.length; i++){
	    			//List<Region> list = iDao.findById(Region.class, array1[i]);
	    			/*if(null != list && list.size()>0){
	    				Region region = list.get(0);
	        			Map<String, String> obj = getAll(region);
	    	    		lo.add(obj);
	        		}*/
		    	}
	    	}
    		
	    	
			
			//导出Excel文件数据 
	    	ExportExcelUtil util = new ExportExcelUtil();
			File file =util.getExcelDemoFile("/template/区域信息表.xlsx");
			
			String sheetName="区域信息表";
			
			wb = util.writeNewTaskExcel(file, sheetName, lo);
			
			String fileName="永弘任务表_" + sdf.format(new Date()) + ".xlsx"; 
			
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
    public Map<String, String> getAll(Region region){
    	Map<String, String> obj = new HashMap<String, String>();
    	//obj.put("taskTitle", task.getTaskTitle());  //任务标题
    	//obj.put("taskToWho", task.getTaskMaster());     //任务来源
    	//obj.put("taskToWhoList", task.getTaskToWho());//分配用户
    	//obj.put("taskType", task.getTaskType());//任务类型
    	//obj.put("commic", task.getCommic());//备注
    	//obj.put("starTime", task.getStarTime());//开始时间
    	//obj.put("overTime", task.getOverTime());//结束时间
    	// i = task.getTaskStatus(); //任务状态
    	/*if(i==1){
    		obj.put("taskStatus", "未开始"); 
    	}else if(i==2){
    		obj.put("taskStatus", "进行中"); 
    	}else{
    		obj.put("taskStatus", "已完成"); 
    	}*/
    	return obj;
    }
	@Override
	public List<Region> queryByPage(int page, int rows, String province,
			String city, String district, String shortcode, String citycode,
			String postcode, String qStarttime, String qEndtime) {
		String sql="FROM Region WHERE 1=1";
		
		if(province!=null&&!province.equals("")&&!province.equals(" ")){
			sql += " and province like '%" + province + "%'";
		}
		if(city!=null&&!city.equals("")&&!city.equals(" ")){
			sql +=" and city like '%" + city + "%'";
		}
	    if(district != null && !district.equals("")&& !district.equals(" ")){
	    	sql += " and district like '%" + district + "%'";
	    }
	    if(shortcode != null && !shortcode.equals("")&& !shortcode.equals(" ")){
	    	sql +=" and shortcode like '%" + shortcode + "%'";
	    }
	    if(citycode != null && !citycode.equals("")&& !citycode.equals(" ")){
	    	sql += " and citycode like '%" + citycode + "%'";
	    }
	    if(postcode != null && !postcode.equals("")&& !postcode.equals(" ")){
	    	sql += " and postcode like '%" + postcode + "%'";
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
		List<Region> list=iDao.queryByPage(page,rows,sql);
		return list;
	}
	@Override
	public Long queryCount(String province, String city, String district,
			String shortcode, String citycode, String postcode,
			String qStarttime, String qEndtime) {
		String sql="FROM Region WHERE 1=1";
		
		if(province!=null&&!province.equals("")&&!province.equals(" ")){
			sql += " and province like '%" + province + "%'";
		}
		if(city!=null&&!city.equals("")&&!city.equals(" ")){
			sql +=" and city like '%" + city + "%'";
		}
	    if(district != null && !district.equals("")&& !district.equals(" ")){
	    	sql += " and district like '%" + district + "%'";
	    }
	    if(shortcode != null && !shortcode.equals("")&& !shortcode.equals(" ")){
	    	sql +=" and shortcode like '%" + shortcode + "%'";
	    }
	    if(citycode != null && !citycode.equals("")&& !citycode.equals(" ")){
	    	sql += " and citycode like '%" + citycode + "%'";
	    }
	    if(postcode != null && !postcode.equals("")&& !postcode.equals(" ")){
	    	sql += " and postcode like '%" + postcode + "%'";
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
		return iDao.count(Region.class,sql);
	}


	


	
	
	
}
