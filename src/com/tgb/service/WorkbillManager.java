package com.tgb.service;

import java.util.List;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.tgb.entity.Function;
import com.tgb.entity.MenuItem;
import com.tgb.entity.NoBill;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;
import com.tgb.entity.WorkBill;

public interface WorkbillManager {

	List<WorkBill> findAllByPage(int page, int rows);

	Long getCount();

	List<WorkBill> findAll();

	void addWorkBill(WorkBill workBill);

	void delWorkBill(String id);


	void updateWorkBill(WorkBill workBill);



	
}
