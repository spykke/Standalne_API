package com.spykke.internal.adcampaign.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

public class OrderDto {

	private long id;
	
	private String orderNo;
	
	private String username;
	
	private int sex;
	
	private Date birthday;
	
	private Integer leaseFrequency;
	
	private String ageGroup;
	
	private String gender;
	
	private String userPackage;
	
	private String powerBankNo;
	
	private String rentCabinet;
	
	private String rentShop;
	
	private String returnCabinet;
	
	private String returnShop;
	
	private int usedTime;
	
	private Date createTime;
	
	private Boolean isDeleted;
	
	
	

	public OrderDto(long id, String orderNo, String username, int sex, Date birthday, Integer leaseFrequency,
			String userPackage, String powerBankNo, String rentCabinet, String rentShop,
			String returnCabinet, String returnShop, int usedTime, Date createTime) {
		super();
		this.id = id;
		this.orderNo = orderNo;
		this.username = username;
		this.sex = sex;
		this.birthday = birthday;
		this.leaseFrequency = leaseFrequency;
		this.userPackage = userPackage;
		this.powerBankNo = powerBankNo;
		this.rentCabinet = rentCabinet;
		this.rentShop = rentShop;
		this.returnCabinet = returnCabinet;
		this.returnShop = returnShop;
		this.usedTime = usedTime;
		this.createTime = createTime;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getLeaseFrequency() {
		return leaseFrequency;
	}

	public void setLeaseFrequency(Integer leaseFrequency) {
		this.leaseFrequency = leaseFrequency;
	}

	public String getAgeGroup() {
		Date today = new Date();
		long age = today.getYear()-birthday.getYear();
		if(age < 18) {
			return "Below 18";
		}
		if(age > 30) {
			return "Below 18";
		}
		return "18 to 30";
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public String getGender() {
		switch (this.sex) {
		case 0:
			gender = "Female";
			break;
		case 1:
			gender = "Male";
			break;
		case 2:
			gender = "Others";
			break;
		case 3:
			gender = "Not Set";
			break;
		default:
			gender = "";
			break;
		}
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserPackage() {
		return userPackage;
	}

	public void setUserPackage(String userPackage) {
		this.userPackage = userPackage;
	}

	public String getPowerBankNo() {
		return powerBankNo;
	}

	public void setPowerBankNo(String powerBankNo) {
		this.powerBankNo = powerBankNo;
	}

	public String getRentCabinet() {
		return rentCabinet;
	}

	public void setRentCabinet(String rentCabinet) {
		this.rentCabinet = rentCabinet;
	}

	public String getRentShop() {
		return rentShop;
	}

	public void setRentShop(String rentShop) {
		this.rentShop = rentShop;
	}

	public String getReturnCabinet() {
		return returnCabinet;
	}

	public void setReturnCabinet(String returnCabinet) {
		this.returnCabinet = returnCabinet;
	}

	public String getReturnShop() {
		return returnShop;
	}

	public void setReturnShop(String returnShop) {
		this.returnShop = returnShop;
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
	
	

}
