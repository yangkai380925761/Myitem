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
@Table(name="BC_Region")
public class Region {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id;
	public static final String REGIONID = "id";
	//省
	@Column(name="province",length=32)
	private String province;
	public static final String PROVINCE = "province";
	//市 
	@Column(name="city",length=50)
	private String city;
	public static final String CITY = "city";
	//区
	@Column(name="district",length=50)
	private String district;
	//邮编
	@Column(name="postcode",length=50)
	private String postcode;
	//简码
	@Column(name="shortcode",length=50)
	private String shortcode;
	//城市编码
	@Column(name="citycode",length=50)
	private String citycode;
	
	//操作人
	@Column(name="CreateBy", length=50, updatable=false)
	private String createBy;
	//创建时间
	@Column(name="createTime")
	private Timestamp createTime;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getDistrict() {
		return district;
	}


	public void setDistrict(String district) {
		this.district = district;
	}


	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public String getShortcode() {
		return shortcode;
	}


	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}


	public String getCitycode() {
		return citycode;
	}


	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}


	public String getCreateBy() {
		return createBy;
	}


	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	

	

	
	
}
