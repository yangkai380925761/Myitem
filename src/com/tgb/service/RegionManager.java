package com.tgb.service;

import java.util.List;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Region;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;

public interface RegionManager {

	List<Region> findAllByPage(int page, int rows);

	Long getCount();

	public void updateStandard(Region region);

	public void delStandard(String id);

	void addStandard(Region region);

	List<Region> findAll();

	
}
