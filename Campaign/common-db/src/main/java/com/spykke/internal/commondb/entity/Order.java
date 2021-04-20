package com.spykke.internal.commondb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order {

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String orderNo;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@OrderColumn(name = "nickname" )
	private AppUser appUser;
	
	@ManyToOne
	@JoinColumn(name = "user_package_id")
	private UserPackage userPackage;
	
	@Column
	private String powerBankNo;
	
	@ManyToOne
	@JoinColumn(name = "rent_cabinet_no", referencedColumnName = "cabinet_no")
	private Cabinet rentCabinet;
	
	@ManyToOne
	@JoinColumn(name = "return_cabinet_no", referencedColumnName = "cabinet_no")
	private Cabinet returnCabinet;
	
	@Column
	private int usedTime;
	
	@Column
	private Date createTime;
	
	@Column
	private Boolean isDeleted; 

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}		
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public UserPackage getUserPackage() {
		return userPackage;
	}

	public void setUserPackage(UserPackage userPackage) {
		this.userPackage = userPackage;
	}

	public String getPowerBankNo() {
		return powerBankNo;
	}

	public void setPowerBankNo(String powerBankNo) {
		this.powerBankNo = powerBankNo;
	}

	public int getUsedTime() {
		return usedTime;
	}

	public void setUsedTime(int usedTime) {
		this.usedTime = usedTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Cabinet getRentCabinet() {
		return rentCabinet;
	}

	public void setRentCabinet(Cabinet rentCabinet) {
		this.rentCabinet = rentCabinet;
	}

	public Cabinet getReturnCabinet() {
		return returnCabinet;
	}

	public void setReturnCabinet(Cabinet returnCabinet) {
		this.returnCabinet = returnCabinet;
	}
	

}
