package com.tgb.service;

import java.util.List;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Role;
import com.tgb.entity.Standard;

public interface StandardManager {

	List<Standard> findAllByPage(int page, int rows);

	Long getCount();

	public void updateStandard(Standard standard);

	public void delStandard(String id);

	 void addStandard(Standard standard);

	List<Standard> findAll();

	List<Standard> queryByPage(int page, int rows, String standardName,
			String minweight, String maxweight, String createBy,
			String qStarttime, String qEndtime);

	Long queryCount(String standardName, String minweight, String maxweight, String createBy, String qStarttime, String qEndtime);

	
}
