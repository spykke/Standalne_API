package com.spykke.subscribe.banner.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="tb_banner_tracking")
public class TrackingBannerAd{
	
	@Id
	@GeneratedValue
	private long id;

	@Column
	private String bannerId;
	
	@Column
	private String stationId;
	
	@Column
	private String userId;
	
	@Column
	private String orderId;
	
	@Column
	private String clientId;
	
	@Column
	private String clientEmail;
	
	@Column
	private String triggerStr;
	
	@Column
	private Date triggerDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTriggerStr() {
		return triggerStr;
	}

	public void setTriggerStr(String triggerStr) {
		this.triggerStr = triggerStr;
	}

	public String getBannerId() {
		return bannerId;
	}

	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	
	public Date getTriggerDate() {
		return triggerDate;
	}

	public void setTriggerDate(Date triggerDate) {
		this.triggerDate = triggerDate;
	}
	

}
