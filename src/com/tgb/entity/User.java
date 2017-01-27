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
@Table(name="T_USER")
public class User {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id;
	public static final String USERID = "id";
	
	@Column(name="userName",length=32)
	private String userName;
	public static final String USERNAME = "userName";
	 
	@Column(name="password",length=32)
	private String password;
	public static final String PASSWORD = "password";
	
	@Column(name="userTrueName",length=50)
	private String userTrueName;
	 public static final String USERTRUENAME = "userTrueName";
	 
	@Column(name="Email",length=50)
	private String  email;
	
	@Column(name="phone",length=50)
	private String  phone;
	
	@Column(name="userType",length=10)
	private String userType;
	
	@Column(name="CreateTime", updatable=false)
	private Date createTime;
	 
	@Column(name="CreateBy", length=50, updatable=false)
	private String createBy;

	//最近登录时间
	@Column(name="loginTime", length=50)
	private Timestamp loginTime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserTrueName() {
		return userTrueName;
	}

	public void setUserTrueName(String userTrueName) {
		this.userTrueName = userTrueName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
}
