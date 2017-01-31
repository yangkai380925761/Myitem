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
import com.tgb.entity.MenuItem;
import com.tgb.entity.Region;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;

@Service
@Transactional
public class RegionManagerImpl implements RegionManager{
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
	public void updateStandard(Region region) {
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
	public void delStandard(String id) {
		/*String hql="delete  Standard s where s.id=?";
		Query query=iDao.getSession().createQuery(hql);
		query.setString(0, id);
		query.executeUpdate();*/
		Staff staff=new Staff();
		Class Staff=staff.getClass();
		iDao.deleteById(Staff, id);
		
	}
	@Override
	public void addStandard(Region region) {
		/*iDao.getSession().save(standard);
		Transaction tran=iDao.getSession().beginTransaction(); 
		iDao.getSession().flush();
		tran.commit();*/
		iDao.add(region);
			
		
	}
	@Override
	public List<Region> findAll() {
		return iDao.findAll("from Staff");
	}


	


	
	
	
}
