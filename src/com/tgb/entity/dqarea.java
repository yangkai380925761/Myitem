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
@Table(name="BC_DQAREA")
public class dqarea {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id;
	public static final String STANDARDID = "id";
	
	@Column(name="dqName",length=32)
	private String dqName;
	public static final String DQNAME = "dqName";
	 
	
	
	@Column(name="createTime")
	private Timestamp createTime;



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getDqName() {
		return dqName;
	}



	public void setDqName(String dqName) {
		this.dqName = dqName;
	}



	public Timestamp getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	

	
	
	 
	

	
	
	
	
}
