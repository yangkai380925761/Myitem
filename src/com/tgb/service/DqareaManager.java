package com.tgb.service;

import java.util.List;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.tgb.entity.Dqarea;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Region;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;

public interface DqareaManager {

	List<Dqarea> findAllByPage(int page, int rows);

	Long getCount();


	List<Staff> findAll();

	void updateDqarea(Dqarea dqarea);

	void addDqarea(Dqarea dqarea);

	void delDqarea(String id);

	List<Dqarea> queryByPage(int page, int rows, String dqName, String did,
			String qStarttime, String qEndtime);

	Long queryCount(String dqName, String did, String qStarttime,
			String qEndtime);

	
}
