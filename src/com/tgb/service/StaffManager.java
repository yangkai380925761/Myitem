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

	public void updateStaff(Staff staff);

	public void delStaff(String id);

	List<Staff> findAll();

	public void addStaff(Staff staff);

	List<Staff> findInfoByName(String name);

	List<Staff> queryByPage(int page, int rows, String staffName, String phone,
			String station, String haspda, String standard, String qStarttime,
			String qEndtime);

	Long queryCount(String staffName, String phone, String station,
			String haspda, String standard, String qStarttime, String qEndtime);



	
}
