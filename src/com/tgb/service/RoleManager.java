package com.tgb.service;

import java.util.List;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.tgb.entity.Function;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Role;

public interface RoleManager {

	List<Role> findAllByPage(int page, int rows);

	Long getCount();

	List<Role> findAll();

	void addRole(Role role);

	void delRole(String id);

	void updateRole(Role role);


	
}
