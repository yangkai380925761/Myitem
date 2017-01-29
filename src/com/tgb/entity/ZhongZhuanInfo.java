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
@Table(name="ZM_ZH0NGZHUANGINFO")
public class ZhongZhuanInfo {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id;
	public static final String ZH0NGZHUANGINFOID = "id";
	
	@Column(name="info",length=32)
	private String info;
	
	@Column(name="arrive",length=32)
	private String arrive;
	
	@Column(name="updateTime")
	private Timestamp updateTime;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}


	public Timestamp getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}


	public String getArrive() {
		return arrive;
	}


	public void setArrive(String arrive) {
		this.arrive = arrive;
	}

	
	

	
	
	
	
}
