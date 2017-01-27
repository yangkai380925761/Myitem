package com.tgb.service;

import java.awt.Menu;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tgb.Myitem.service.impl.GeneralDao;
import com.tgb.entity.MenuItem;

@Service
@Transactional
public class MenuItemManagerImpl implements MenuItemManager{
	@Autowired
	private GeneralDao gDao;
	@Override
	public List<MenuItem> findAll() {
		List<MenuItem> list=null;
		try {
			 list=gDao.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<MenuItem> sysMenuItem(int parentItemId, int menuId) {
		List<MenuItem> list=null;
		try {
			 list=gDao.sysMenuItem( parentItemId, menuId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
