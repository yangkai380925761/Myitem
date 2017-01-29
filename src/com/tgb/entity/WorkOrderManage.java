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
	private String id;
	public static final String WORKORDERID = "id";
	
	@Column(name="arrivecity",length=32)
	private String arrivecity;
	
	@Column(name="num",length=32)
	private String num;
	
	@Column(name="weight",length=32)
	private String weight;
	
	@Column(name="floadreqr",length=32)
	private String floadreqr;
	
	@Column(name="prodtimelimit",length=32)
	private String prodtimelimit;
	
	@Column(name="prodtype",length=32)
	private String prodtype;
	
	@Column(name="product",length=32)
	private String product;
	
	@Column(name="sendername",length=32)
	private String sendername;
	
	@Column(name="senderphone",length=32)
	private String senderphone;
	
	@Column(name="senderaddr",length=32)
	private String senderaddr;
	
	@Column(name="receivername",length=32)
	private String receivername;
	
	@Column(name="receiverphone",length=32)
	private String receiverphone;
	
	@Column(name="receiveraddr",length=32)
	private String receiveraddr;
	
	@Column(name="feeitemnum",length=32)
	private String feeitemnum;
	
	@Column(name="actlweit",length=32)
	private String actlweit;
	
	@Column(name="vol",length=32)
	private String vol;
	
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
