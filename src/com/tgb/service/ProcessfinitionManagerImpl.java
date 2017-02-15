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
import com.tgb.entity.Subarea;
import com.tgb.entity.WorkBill;

@Service
@Transactional
public class ProcessfinitionManagerImpl implements ProcessfinitionManager{
	@Autowired
	private IDao<WorkBill> iDao;
	
	@Override
	public List<WorkBill> findAll() {
		return iDao.findAll("from WorkBill");
	}
	
	
}
