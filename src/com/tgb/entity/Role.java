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
@Table(name="T_Role")
public class Role {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id;
	public static final String ROLEID = "id";
	
	@Column(name="roleName",length=32)
	private String roleName;
	public static final String ROLENAME = "roleName";
	 
	@Column(name="description",length=50)
	private String description;
	public static final String DESCRIPTION = "description";

	@Column(name="CreateBy", length=50, updatable=false)
	private String createBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	

	
	
}
