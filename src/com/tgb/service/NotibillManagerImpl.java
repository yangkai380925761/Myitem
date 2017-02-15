package com.tgb.service;

import java.awt.Menu;



import java.sql.SQLException;
import java.util.List;

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

@Service
@Transactional
public class NotibillManagerImpl implements NotibillManager{
	@Autowired
	private IDao<NoBill> iDao;
	@Override
	public List<NoBill> findAllByPage(int page, int rows) {
		String hql="from NoBill";
		List<NoBill> list=iDao.findByPage(hql,page,rows);
		return list;
	}
	@Override
	public Long getCount() {
		String hql="select count(*) from NoBill";
		Long  count=iDao.count(hql);
		return count;
	}
	@Override
	public void updateNoBill(NoBill noBill) {
		/*String hql="update Standard u set u.standardName = ?,u.minweight=?,u.maxweight=? where u.id = ?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, standard.getStandardName());
		query.setDouble(1, standard.getMinweight());
		query.setDouble(2, standard.getMaxweight());
		query.setString(3, standard.getId());
		query.executeUpdate();*/
		iDao.update(noBill);
	}
	@Override
	public void delNoBill(String id) {
		/*String hql="delete  Standard s where s.id=?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();*/
		NoBill noBill=new NoBill();
		Class NoBill=noBill.getClass();
		iDao.deleteById(NoBill, id);
		
	}
	@Override
	public void addNoBill(NoBill noBill) {
		/*iDao.getSession().save(standard);
		Transaction tran=iDao.getSession().beginTransaction(); 
		iDao.getSession().flush();
		tran.commit();*/
		iDao.add(noBill);
			
		
	}
	@Override
	public List<NoBill> findAll() {
		return iDao.findAll("from NoBill");
	}
	


	


	
	
	
}
