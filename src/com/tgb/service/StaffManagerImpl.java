package com.tgb.service;

import java.awt.Menu;



import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.eclipse.jdt.internal.compiler.lookup.ClassScope;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgb.Myitem.service.impl.IDao;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;

@Service
@Transactional
public class StaffManagerImpl implements StaffManager{
	@Autowired
	private IDao<Staff> iDao;
	@Override
	public List<Staff> findAllByPage(int page, int rows) {
		String hql="from Staff";
		List<Staff> list=iDao.findByPage(hql,page,rows);
		return list;
	}
	@Override
	public Long getCount() {
		String hql="select count(*) from Staff";
		Long  count=iDao.count(hql);
		return count;
	}
	@Override
	public void updateStaff(Staff Staff) {
		/*String hql="update Standard u set u.standardName = ?,u.minweight=?,u.maxweight=? where u.id = ?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, standard.getStandardName());
		query.setDouble(1, standard.getMinweight());
		query.setDouble(2, standard.getMaxweight());
		query.setString(3, standard.getId());
		query.executeUpdate();*/
		iDao.update(Staff);
	}
	@Override
	public void delStaff(String id) {
		/*String hql="delete  Standard s where s.id=?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();*/
		Staff staff=new Staff();
		Class Staff=staff.getClass();
		iDao.deleteById(Staff, id);
		
	}
	@Override
	public void addStaff(Staff staff) {
		/*iDao.getSession().save(standard);
		Transaction tran=iDao.getSession().beginTransaction(); 
		iDao.getSession().flush();
		tran.commit();*/
		iDao.add(staff);
			
		
	}
	@Override
	public List<Staff> findAll() {
		return iDao.findAll("from Staff");
	}
	@Override
	public List<Staff> findInfoByName(String name) {
		String hql="from Staff where staffName=?";
		return iDao.findInfoByName(hql,name);
	}
	@Override
	public List<Staff> queryByPage(int page, int rows, String staffName,
			String phone, String station, String haspda, String standard,
			String qStarttime, String qEndtime) {
		String sql="FROM Staff WHERE 1=1";
		
		if(staffName!=null&&!staffName.equals("")&&!staffName.equals(" ")){
			sql += " and staffName like '%" + staffName + "%'";
		}
		if(phone!=null&&!phone.equals("")&&!phone.equals(" ")){
			sql +=" and phone like '%" + phone + "%'";
		}
	    if(station != null && !station.equals("")&& !station.equals(" ")){
	    	sql += " and station like '%" + station + "%'";
	    }
	    if(haspda != null && !haspda.equals("")&& !haspda.equals(" ")){
	    	sql +=" and haspda like '%" + haspda + "%'";
	    }
	    if(standard != null && !standard.equals("")&& !standard.equals(" ")){
	    	sql += " and standard like '%" + standard + "%'";
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
		List<Staff> list=iDao.queryByPage(page,rows,sql);
		return list;
	}
	@Override
	public Long queryCount(String staffName, String phone, String station,
			String haspda, String standard, String qStarttime, String qEndtime) {
		String sql="FROM Staff WHERE 1=1";
		
		if(staffName!=null&&!staffName.equals("")&&!staffName.equals(" ")){
			sql += " and staffName like '%" + staffName + "%'";
		}
		if(phone!=null&&!phone.equals("")&&!phone.equals(" ")){
			sql +=" and phone like '%" + phone + "%'";
		}
	    if(station != null && !station.equals("")&& !station.equals(" ")){
	    	sql += " and station like '%" + station + "%'";
	    }
	    if(haspda != null && !haspda.equals("")&& !haspda.equals(" ")){
	    	sql +=" and haspda like '%" + haspda + "%'";
	    }
	    if(standard != null && !standard.equals("")&& !standard.equals(" ")){
	    	sql += " and standard like '%" + standard + "%'";
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
		return iDao.count(Staff.class,sql);
	}


	


	
	
	
}
