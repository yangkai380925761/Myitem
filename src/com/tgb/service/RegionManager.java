package com.tgb.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Region;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;

public interface RegionManager {

	List<Region> findAllByPage(int page, int rows);

	Long getCount();

	public void updateRegion(Region region);

	public void delRegion(String id);

	public void addRegion(Region region);

	List<Region> findAll();

	void exportExcel(HttpServletRequest request, HttpServletResponse response);

	List<Region> queryByPage(int page, int rows, String province, String city,
			String district, String shortcode, String citycode,
			String postcode, String qStarttime, String qEndtime);

	Long queryCount(String province, String city, String district,
			String shortcode, String citycode, String postcode,
			String qStarttime, String qEndtime);




	
}
