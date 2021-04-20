package com.spykke.internal.adcampaign.dao;

public class CouponUser {
	
	private String couponCode;
	
	private String couponCodeChild;
	
	private long userId;
	
	private String username;
	
	private Integer useCount;
	
	
	public CouponUser(String couponCode, String couponCodeChild, long userId, String username, Integer useCount) {
		super();
		this.couponCode = couponCode;
		this.couponCodeChild = couponCodeChild;
		this.userId = userId;
		this.username = username;
		this.useCount = useCount;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponCodeChild() {
		return couponCodeChild;
	}

	public void setCouponCodeChild(String couponCodeChild) {
		this.couponCodeChild = couponCodeChild;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}
	
	

}
