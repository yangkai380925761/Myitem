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
import com.tgb.entity.Standard;

@Service
@Transactional
public class StandardManagerImpl implements StandardManager{
	@Autowired
	private IDao<Standard> iDao;
	@Override
	public List<Standard> findAllByPage(int page, int rows) {
		String hql="from Standard";
		List<Standard> list=iDao.findByPage(hql,page,rows);
		return list;
	}
	@Override
	public Long getCount() {
		String hql="select count(*) from Standard";
		Long  count=iDao.count(hql);
		return count;
	}
	@Override
	public void updateStandard(Standard standard) {
		/*String hql="update Standard u set u.standardName = ?,u.minweight=?,u.maxweight=? where u.id = ?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, standard.getStandardName());
		query.setDouble(1, standard.getMinweight());
		query.setDouble(2, standard.getMaxweight());
		query.setString(3, standard.getId());
		query.executeUpdate();*/
		iDao.update(standard);
	}
	@Override
	public void delStandard(String id) {
		/*String hql="delete  Standard s where s.id=?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();*/
		Standard standard=new Standard();
		Class Standard=standard.getClass();
		iDao.deleteById(Standard, id);
		
	}
	@Override
	public void addStandard(Standard standard) {
		/*iDao.getSession().save(standard);
		Transaction tran=iDao.getSession().beginTransaction(); 
		iDao.getSession().flush();
		tran.commit();*/
		iDao.add(standard);
			
		
	}
	@Override
	public List<Standard> findAll() {
		return iDao.findAll("from Standard");
	}
	@Override
	public List<Standard> queryByPage(int page, int rows, String standardName,
			String minweight, String maxweight, String createBy,
			String qStarttime, String qEndtime) {
		String sql="FROM Standard WHERE 1=1";
		
		if(standardName!=null&&!standardName.equals("")&&!standardName.equals(" ")){
			sql += " and standardName like '%" + standardName + "%'";
		}
		if(minweight!=null&&!minweight.equals("")&&!minweight.equals(" ")){
			sql += " and minweight >= '" + minweight + "'";
		}
	    if(maxweight != null && !maxweight.equals("")&& !maxweight.equals(" ")){
	    	sql += " and maxweight <= '" + maxweight + "'";
	    }
	    if(createBy != null && !createBy.equals("")&& !createBy.equals(" ")){
	    	sql += " and createBy = '" + createBy + "'";
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
		List<Standard> list=iDao.queryByPage(page,rows,sql);
		return list;
	}
	
	@Override
	public Long queryCount(String standardName, String minweight,
			String maxweight, String createBy, String qStarttime,
			String qEndtime) {
		String sql="FROM Standard WHERE 1=1";
		
		if(standardName!=null&&!standardName.equals("")&&!standardName.equals(" ")){
			sql += " and standardName like '%" + standardName + "%'";
		}
		if(minweight!=null&&!minweight.equals("")&&!minweight.equals(" ")){
			sql += " and minweight >= '" + minweight + "'";
		}
	    if(maxweight != null && !maxweight.equals("")&& !maxweight.equals(" ")){
	    	sql += " and maxweight <= '" + maxweight + "'";
	    }
	    if(createBy != null && !createBy.equals("")&& !createBy.equals(" ")){
	    	sql += " and createBy = '" + createBy + "'";
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
		return iDao.count(Standard.class,sql);
	}


	


	
	
	
}
