package com.tgb.service;

import java.util.List;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.tgb.entity.MenuItem;

public interface MenuItemManager {

	public  List<MenuItem> findAll();

	public List<MenuItem> sysMenuItem(int parentItemId, int menuId);

	
}
