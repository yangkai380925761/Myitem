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
	
	@Column(name="functionName",length=32)
	private String functionName;
	 
	@Column(name="description", length=50)
	private String description;
	 
	@Column(name="generateMenu",length=20)
	private double  generateMenu;
	
	@Column(name="zindex",length=20)
	private double zindex;
	
	@Column(name="page",length=20)
	private double page;
	
	@Column(name="parentFunction",length=20)
	private double parentFunction;
	
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

	public double getGenerateMenu() {
		return generateMenu;
	}

	public void setGenerateMenu(double generateMenu) {
		this.generateMenu = generateMenu;
	}

	public double getZindex() {
		return zindex;
	}

	public void setZindex(double zindex) {
		this.zindex = zindex;
	}

	public double getPage() {
		return page;
	}

	public void setPage(double page) {
		this.page = page;
	}

	public double getParentFunction() {
		return parentFunction;
	}

	public void setParentFunction(double parentFunction) {
		this.parentFunction = parentFunction;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	

	
	 
	

	
	
	
	
}
