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
@Table(name="BC_STAFF")
public class Staff {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id;//取派员编号
	public static final String STAFFDID = "id";
	
	//取派人姓名
	@Column(name="staffName",length=32)
	private String staffName;
	public static final String STAFFNAME = "staffName";
	
	 //联系电话
	@Column(name="phone", length=50)
	private String phone;
	
	//是否有移动设备 
	@Column(name="haspda",length=20)
	private String  haspda;
	
	//所属单位
	@Column(name="station")
	private String station;
	
	//取派标准
	@Column(name="standard")
	private String standard;
	
	@Column(name="createTime")
	private Timestamp createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHaspda() {
		return haspda;
	}

	public void setHaspda(String haspda) {
		this.haspda = haspda;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	
	 
	

	
	
	
	
}
