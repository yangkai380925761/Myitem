package com.tgb.service;

import java.awt.Menu;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgb.Myitem.service.impl.IDao;
import com.tgb.entity.Function;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Role;

@Service
@Transactional
public class RoleManagerImpl implements RoleManager{
	@Autowired
	private IDao<Role> iDao;
	
	@Override
	public List<Role> findAllByPage(int page, int rows) {
		String hql="from Role";
		List<Role> list=iDao.findByPage(hql,page,rows);
		return list;
	}
	@Override
	public Long getCount() {
		String hql="select count(*) from Role";
		Long  count=iDao.count(hql);
		return count;
	}
	@Override
	public void updateRole(Role role) {
		/*String hql="update Standard u set u.standardName = ?,u.minweight=?,u.maxweight=? where u.id = ?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, standard.getStandardName());
		query.setDouble(1, standard.getMinweight());
		query.setDouble(2, standard.getMaxweight());
		query.setString(3, standard.getId());
		query.executeUpdate();*/
		iDao.update(role);
	}
	@Override
	public void delRole(String id) {
		/*String hql="delete  Standard s where s.id=?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();*/
		Role role=new Role();
		Class Role=role.getClass();
		iDao.deleteById(Role, id);
		
	}
	@Override
	public void addRole(Role role) {
		/*iDao.getSession().save(standard);
		Transaction tran=iDao.getSession().beginTransaction(); 
		iDao.getSession().flush();
		tran.commit();*/
		iDao.add(role);
			
		
	}
	@Override
	public List<Role> findAll() {
		return iDao.findAll("from Role");
	}
	


	


	
	
	
}
