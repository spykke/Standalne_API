package com.spykke.internal.adcampaign.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "tb_banner_station")
public class BannerStation {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="banner_id",nullable = false)
	private BannerAd banner;
	
	@Column
	private String stationId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public BannerAd getBanner() {
		return banner;
	}

	public void setBanner(BannerAd banner) {
		this.banner = banner;
	}
	
	

		
	

}
