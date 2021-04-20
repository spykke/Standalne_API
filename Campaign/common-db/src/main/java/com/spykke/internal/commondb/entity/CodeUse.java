package com.spykke.internal.commondb.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="tb_code_use")
public class CodeUse {
	
	@Id
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "code_child_id")
	private CouponCodeChild couponCodeChild;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private AppUser appUser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CouponCodeChild getCouponCodeChild() {
		return couponCodeChild;
	}

	public void setCouponCodeChild(CouponCodeChild couponCodeChild) {
		this.couponCodeChild = couponCodeChild;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	

}
