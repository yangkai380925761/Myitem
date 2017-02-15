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
@Table(name="SL_WORKBILL")
public class WorkBill {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id;//编号
	public static final String WORKBILLID = "id";
	//工单类型
	@Column(name="type",length=32)
	private String type;
	//取件状态
	@Column(name="pickstate", length=50)
	private String pickstate;
	 //追单次数
	@Column(name="attachbilltimes",length=20)
	private String  attachbilltimes;
	//备注
	@Column(name="remark")
	private String remark;
	//关联取派员
	@Column(name="Staff")
	private String Staff;
	//关联通知单
	@Column(name="noticeBill")
	private String noticeBill;
	//修改时间
	@Column(name="updateTime")
	private Timestamp updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPickstate() {
		return pickstate;
	}

	public void setPickstate(String pickstate) {
		this.pickstate = pickstate;
	}

	public String getAttachbilltimes() {
		return attachbilltimes;
	}

	public void setAttachbilltimes(String attachbilltimes) {
		this.attachbilltimes = attachbilltimes;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getStaff() {
		return Staff;
	}

	public void setStaff(String staff) {
		Staff = staff;
	}

	public String getNoticeBill() {
		return noticeBill;
	}

	public void setNoticeBill(String noticeBill) {
		this.noticeBill = noticeBill;
	}

	
	
	
	 
	

	
	
	
	
}
