package com.tgb.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.tgb.entity.MenuItem;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;
import com.tgb.entity.Subarea;

public interface SubareaManager {

	List<Subarea> findAllByPage(int page, int rows);

	Long getCount();



	List<Subarea> findAll();

	void addSubuarea(Subarea subarea);

	void updateSubarea(Subarea subarea);

	void delSubarea(String id);

	void exportExcel(HttpServletRequest request, HttpServletResponse response);

	List<Subarea> queryByPage(int page, int rows, String fid,
			String addressName, String startNum, String endNum,
			String position, String hasSingle, String qStarttime,
			String qEndtime);

	Long queryCount(String fid, String addressName, String startNum,
			String endNum, String position, String hasSingle,
			String qStarttime, String qEndtime);

	
}
