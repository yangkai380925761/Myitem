package com.tgb.entity;

import java.sql.Timestamp;

import java.util.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="AUTO_FUNTION")
public class Function {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id;
	public static final String FUNTIONID = "id";
	//权限名称
	@Column(name="functionName",length=32)
	private String functionName;
	//	 描述
	@Column(name="description", length=50)
	private String description;
	 //是否生成菜单（1：生成菜单 0：不生成菜单）
	@Column(name="generateMenu",length=20)
	private String  generateMenu;
	//优先级
	@Column(name="zindex",length=20)
	private String zindex;
	//路径
	@Column(name="page",length=20)
	private String page;
	//父功能
	@Column(name="parentFunction",length=20)
	private String parentFunction;
	
	@Column(name="CreateTime")
	private Timestamp createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenerateMenu() {
		return generateMenu;
	}

	public void setGenerateMenu(String generateMenu) {
		this.generateMenu = generateMenu;
	}

	public String getZindex() {
		return zindex;
	}

	public void setZindex(String zindex) {
		this.zindex = zindex;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getParentFunction() {
		return parentFunction;
	}

	public void setParentFunction(String parentFunction) {
		this.parentFunction = parentFunction;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	

	
	 
	

	
	
	
	
}
