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
@Table(name="BC_STANDARD")
public class Standard {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id;
	public static final String STANDARDID = "id";
	
	@Column(name="standardName",length=32)
	private String standardName;
	public static final String STANDARDNAME = "standardName";
	 
	@Column(name="CreateBy", length=50)
	private String createBy;
	 
	@Column(name="minweight",length=20)
	private double  minweight;
	
	@Column(name="maxweight",length=20)
	private double maxweight;
	
	@Column(name="CreateTime")
	private Timestamp createTime;
	
	@Column(name="updateTime")
	private Timestamp updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public double getMinweight() {
		return minweight;
	}

	public void setMinweight(double minweight) {
		this.minweight = minweight;
	}

	public double getMaxweight() {
		return maxweight;
	}

	public void setMaxweight(double maxweight) {
		this.maxweight = maxweight;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	 
	

	
	
	
	
}
