package com.tgb.service;

import java.util.List;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;

public interface StaffManager {

	List<Staff> findAllByPage(int page, int rows);

	Long getCount();

	public void updateStandard(Staff staff);

	public void delStandard(String id);

	 void addStandard(Staff staff);

	List<Staff> findAll();

	
}
