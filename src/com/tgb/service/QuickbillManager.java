package com.tgb.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.tgb.entity.Function;
import com.tgb.entity.MenuItem;
import com.tgb.entity.NoBill;
import com.tgb.entity.Role;
import com.tgb.entity.Staff;
import com.tgb.entity.Standard;
import com.tgb.entity.WorkBill;
import com.tgb.entity.WorkOrderManage;

public interface QuickbillManager {

	List<WorkOrderManage> findAllByPage(int page, int rows);

	Long getCount();

	List<WorkOrderManage> findAll();

	void addWorkOrderManage(WorkOrderManage workOrderManage);

	void delWorkBill(String id);

	void exportExcel(HttpServletRequest request, HttpServletResponse response);



	
}
