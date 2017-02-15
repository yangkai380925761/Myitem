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
import com.tgb.entity.Dqarea;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Region;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;

@Service
@Transactional
public class DqareaManagerImpl implements DqareaManager{
	@Autowired
	private IDao<Dqarea> iDao;
	@Override
	public List<Dqarea> findAllByPage(int page, int rows) {
		String hql="from Dqarea";
		List<Dqarea> list=iDao.findByPage(hql,page,rows);
		return list;
	}
	@Override
	public Long getCount() {
		String hql="select count(*) from Dqarea";
		Long  count=iDao.count(hql);
		return count;
	}
	@Override
	public void updateDqarea(Dqarea dqarea) {
		/*String hql="update Standard u set u.standardName = ?,u.minweight=?,u.maxweight=? where u.id = ?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, standard.getStandardName());
		query.setDouble(1, standard.getMinweight());
		query.setDouble(2, standard.getMaxweight());
		query.setString(3, standard.getId());
		query.executeUpdate();*/
		iDao.update(dqarea);
	}
	@Override
	public void delDqarea(String id) {
		/*String hql="delete  Standard s where s.id=?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();*/
		Dqarea dqarea=new Dqarea();
		Class Dqarea=dqarea.getClass();
		iDao.deleteById(Dqarea, id);
		
	}
	@Override
	public void addDqarea(Dqarea dqarea) {
		/*iDao.getSession().save(standard);
		Transaction tran=iDao.getSession().beginTransaction(); 
		iDao.getSession().flush();
		tran.commit();*/
		iDao.add(dqarea);
			
		
	}
	@Override
	public List<Staff> findAll() {
		return iDao.findAll("from Staff");
	}
	@Override
	public List<Dqarea> queryByPage(int page, int rows, String dqName,
			String did, String qStarttime, String qEndtime) {
		String sql="FROM Dqarea WHERE 1=1";
		
		if(dqName!=null&&!dqName.equals("")&&!dqName.equals(" ")){
			sql += " and dqName like '%" + dqName + "%'";
		}
		if(did!=null&&!did.equals("")&&!did.equals(" ")){
			sql +=" and did like '%" + did + "%'";
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
		List<Dqarea> list=iDao.queryByPage(page,rows,sql);
		return list;
	}
	@Override
	public Long queryCount(String dqName, String did, String qStarttime,
			String qEndtime) {
		String sql="FROM Dqarea WHERE 1=1";
		
		if(dqName!=null&&!dqName.equals("")&&!dqName.equals(" ")){
			sql += " and dqName like '%" + dqName + "%'";
		}
		if(did!=null&&!did.equals("")&&!did.equals(" ")){
			sql +=" and did like '%" + did + "%'";
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
		return iDao.count(Dqarea.class,sql);
	}
	


	


	
	
	
}
