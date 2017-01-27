package com.tgb.entity;

import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_MENUITEM")
public class MenuItem {
	@Id
	@GenericGenerator (name="idgene",strategy="native")
	@GeneratedValue(generator="idgene")
	@Column(name = "itemId")
	private Integer itemId;
	
	@Column(name="itemName",length=50)
	private String itemName;
	
	@Column(name="menuId")
	private Integer menuId;
	
	@Column(name="parentItemId")
	private Integer parentItemId;
	
	@Column(name="itemUrl",length=100)
	private String itemUrl;
	
	@Column(name="itemType",length=100)
	private String itemType;
	
	@Column(name="itemStatus")
	private Integer itemStatus;
	
	@Column(name="description")
	private String description;
	
	@Column(name="createBy",length=50)
	private String createBy;
	
	@Column(name="createTime")
	private Timestamp createTime;
	
	@Column(name="updateTime")
	private Timestamp updateTime;
	
	@Column(name="updateBy",length=50)
	private String updateBy;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(Integer parentItemId) {
		this.parentItemId = parentItemId;
	}

	public String getItemUrl() {
		return itemUrl;
	}

	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public Integer getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(Integer itemStatus) {
		this.itemStatus = itemStatus;
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

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	
}
