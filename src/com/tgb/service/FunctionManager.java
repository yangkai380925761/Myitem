package com.tgb.service;

import java.util.List;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.tgb.entity.Function;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;

public interface FunctionManager {

	List<Function> findAllByPage(int page, int rows);

	Long getCount();

	List<Function> findAll();

	void addFunction(Function function);

	void delFunction(String id);

	void updateFunction(Function function);



	
}
