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
@Table(name="BC_QUAREA")
public class Subarea {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id;
	public static final String FQID = "id";
	//关键字
	@Column(name="addressName",length=32)
	private String addressName;
	//起始号
	@Column(name="startNum", length=50)
	private String startNum;
	//结束号
	@Column(name="endNum",length=20)
	private String  endNum;
	//是否分单双号
	@Column(name="hasSingle")
	private String hasSingle;
	//区分位置信息
	@Column(name="position")
	private String position;
	
	//关联区域
	@Column(name="region")
	private String region;
	
	@Column(name="createTime")
	private Timestamp createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getStartNum() {
		return startNum;
	}

	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}

	public String getEndNum() {
		return endNum;
	}

	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}

	public String getHasSingle() {
		return hasSingle;
	}

	public void setHasSingle(String hasSingle) {
		this.hasSingle = hasSingle;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	
	
	 
	

	
	
	
	
}
