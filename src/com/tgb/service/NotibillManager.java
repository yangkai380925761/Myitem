package com.tgb.service;

import java.util.List;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.tgb.entity.Function;
import com.tgb.entity.MenuItem;
import com.tgb.entity.NoBill;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;

public interface NotibillManager {

	List<NoBill> findAllByPage(int page, int rows);

	Long getCount();

	List<NoBill> findAll();

	void addNoBill(NoBill noBill);

	void delNoBill(String id);

	void updateNoBill(NoBill noBill);



	
}
