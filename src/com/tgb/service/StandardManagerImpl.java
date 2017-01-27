package com.tgb.service;

import java.awt.Menu;



import java.sql.SQLException;
import java.util.List;

import org.eclipse.jdt.internal.compiler.lookup.ClassScope;
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
		iDao.update(standard);
	}
	@Override
	public void delStandard(String id) {
		Class  standard = null;
		iDao.deleteById(standard,id);
	}


	


	
	
	
}
