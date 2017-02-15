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
public class Dqarea {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id;
	public static final String DQAREAID = "id";
	//定区名称
	@Column(name="dqName",length=32)
	private String dqName;
	public static final String DQNAME = "dqName";
	//负责人
	@Column(name="did")
	private String did;
	//负责人
	@Column(name="staff")
	private String staff;
	//关联分区
	@Column(name="subarea")
	private String subarea;
	
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



	public String getStaff() {
		return staff;
	}



	public void setStaff(String staff) {
		this.staff = staff;
	}



	public String getSubarea() {
		return subarea;
	}



	public void setSubarea(String subarea) {
		this.subarea = subarea;
	}



	public String getDid() {
		return did;
	}



	public void setDid(String did) {
		this.did = did;
	}

	

	
	
	 
	

	
	
	
	
}
