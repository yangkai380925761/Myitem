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
@Table(name="SL_NOTBILL")
public class NoBill {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id;//通知单号
	public static final String NOTBILLID = "id";
	//客户单号
	@Column(name="customerId",length=32)
	private String customerId;
	//客户姓名
	@Column(name="customerName",length=32)
	private String customerName;
	//联系人
	@Column(name="linkman",length=32)
	private String linkman;
	//联系电话
	@Column(name="linkNum",length=32)
	private String linkNum;
	//取件地址
	@Column(name="pickaddress",length=32)
	private String pickaddress;
	//到达城市
	@Column(name="arrivecity",length=32)
	private String arrivecity;
	//数量
	@Column(name="num",length=32)
	private String num;
	//货物名称
	@Column(name="product",length=32)
	private String product;
	//取件日期
	@Column(name="pickdate",length=32)
	private String pickdate;
	//重量
	@Column(name="weight",length=32)
	private String weight;
	//体积
	@Column(name="volume",length=32)
	private String volume;
	//备注
	@Column(name="remark",length=32)
	private String remark;
	//业务受理人
	@Column(name="user")
	private String user;
	//取派员
	@Column(name="staff")
	private String staff;
	
	@Column(name="createTime")
	private Timestamp createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkNum() {
		return linkNum;
	}

	public void setLinkNum(String linkNum) {
		this.linkNum = linkNum;
	}

	public String getPickaddress() {
		return pickaddress;
	}

	public void setPickaddress(String pickaddress) {
		this.pickaddress = pickaddress;
	}

	public String getArrivecity() {
		return arrivecity;
	}

	public void setArrivecity(String arrivecity) {
		this.arrivecity = arrivecity;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getPickdate() {
		return pickdate;
	}

	public void setPickdate(String pickdate) {
		this.pickdate = pickdate;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	


	

	

	
	
	 
	

	
	
	
	
}
