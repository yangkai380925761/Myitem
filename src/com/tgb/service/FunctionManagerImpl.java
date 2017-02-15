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
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;

@Service
@Transactional
public class FunctionManagerImpl implements FunctionManager{
	@Autowired
	private IDao<Function> iDao;
	@Override
	public List<Function> findAllByPage(int page, int rows) {
		String hql="from Function";
		List<Function> list=iDao.findByPage(hql,page,rows);
		return list;
	}
	@Override
	public Long getCount() {
		String hql="select count(*) from Function";
		Long  count=iDao.count(hql);
		return count;
	}
	@Override
	public void updateFunction(Function function) {
		/*String hql="update Standard u set u.standardName = ?,u.minweight=?,u.maxweight=? where u.id = ?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, standard.getStandardName());
		query.setDouble(1, standard.getMinweight());
		query.setDouble(2, standard.getMaxweight());
		query.setString(3, standard.getId());
		query.executeUpdate();*/
		iDao.update(function);
	}
	@Override
	public void delFunction(String id) {
		/*String hql="delete  Standard s where s.id=?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();*/
		Function function=new Function();
		Class Function=function.getClass();
		iDao.deleteById(Function, id);
		
	}
	@Override
	public void addFunction(Function function) {
		/*iDao.getSession().save(standard);
		Transaction tran=iDao.getSession().beginTransaction(); 
		iDao.getSession().flush();
		tran.commit();*/
		iDao.add(function);
			
		
	}
	@Override
	public List<Function> findAll() {
		return iDao.findAll("from Function");
	}
	


	


	
	
	
}
