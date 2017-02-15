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
@Table(name="SL_WORKORDER")
public class WorkOrderManage {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=32)
	private String id; //工作单编号
	public static final String WORKORDERID = "id";
	//到达城市
	@Column(name="arrivecity",length=32)
	private String arrivecity;
	//数量
	@Column(name="num",length=32)
	private String num;
	//重量
	@Column(name="weight",length=32)
	private String weight;
	//配载要求
	@Column(name="floadreqr",length=32)
	private String floadreqr;
	//产品时限
	@Column(name="prodtimelimit",length=32)
	private String prodtimelimit;
	//产品类型
	@Column(name="prodtype",length=32)
	private String prodtype;
	//货物名称
	@Column(name="product",length=32)
	private String product;
	//发货人姓名
	@Column(name="sendername",length=32)
	private String sendername;
	//发货人电话
	@Column(name="senderphone",length=32)
	private String senderphone;
	//发货人地址
	@Column(name="senderaddr",length=32)
	private String senderaddr;
	//收货人姓名
	@Column(name="receivername",length=32)
	private String receivername;
	//收获人电话
	@Column(name="receiverphone",length=32)
	private String receiverphone;
	//收获人地址
	@Column(name="receiveraddr",length=32)
	private String receiveraddr;
	//计费计数
	@Column(name="feeitemnum",length=32)
	private String feeitemnum;
	//实际重量
	@Column(name="actlweit",length=32)
	private String actlweit;
	//体积
	@Column(name="vol",length=32)
	private String vol;
	//为工作流预留的字段（0：未审批 1：已审批）
	@Column(name="managerCheck",length=32)
	private String managerCheck;
	
	@Column(name="updateTime")
	private Timestamp updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getFloadreqr() {
		return floadreqr;
	}

	public void setFloadreqr(String floadreqr) {
		this.floadreqr = floadreqr;
	}

	public String getProdtimelimit() {
		return prodtimelimit;
	}

	public void setProdtimelimit(String prodtimelimit) {
		this.prodtimelimit = prodtimelimit;
	}

	public String getProdtype() {
		return prodtype;
	}

	public void setProdtype(String prodtype) {
		this.prodtype = prodtype;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getSendername() {
		return sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	public String getSenderphone() {
		return senderphone;
	}

	public void setSenderphone(String senderphone) {
		this.senderphone = senderphone;
	}

	public String getSenderaddr() {
		return senderaddr;
	}

	public void setSenderaddr(String senderaddr) {
		this.senderaddr = senderaddr;
	}

	public String getReceivername() {
		return receivername;
	}

	public void setReceivername(String receivername) {
		this.receivername = receivername;
	}

	public String getReceiverphone() {
		return receiverphone;
	}

	public void setReceiverphone(String receiverphone) {
		this.receiverphone = receiverphone;
	}

	public String getReceiveraddr() {
		return receiveraddr;
	}

	public void setReceiveraddr(String receiveraddr) {
		this.receiveraddr = receiveraddr;
	}

	public String getFeeitemnum() {
		return feeitemnum;
	}

	public void setFeeitemnum(String feeitemnum) {
		this.feeitemnum = feeitemnum;
	}

	public String getActlweit() {
		return actlweit;
	}

	public void setActlweit(String actlweit) {
		this.actlweit = actlweit;
	}

	public String getVol() {
		return vol;
	}

	public void setVol(String vol) {
		this.vol = vol;
	}

	public String getManagerCheck() {
		return managerCheck;
	}

	public void setManagerCheck(String managerCheck) {
		this.managerCheck = managerCheck;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	
	


	

	

	
	
	 
	

	
	
	
	
}
