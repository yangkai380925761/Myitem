package com.tgb.service;

import java.awt.Menu;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgb.Myitem.service.impl.IDao;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Role;

@Service
@Transactional
public class RoleManagerImpl implements RoleManager{
	@Autowired
	private IDao<Role> iDao;
	@Override
	public List<Role> findAll() {
		String hql="from Role";
		List<Role> list=iDao.findAll(hql);
		return list;
	}


	


	
	
	
}
