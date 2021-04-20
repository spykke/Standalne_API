package com.spykke.internal.adcampaigndb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.spykke.internal.commondb.entity.PowerBank;

@Entity
public class CampaignPowerBank {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="campaign_id",nullable = false)
	private Campaign campaign;
	
	@ManyToOne
	@JoinColumn(name="powerbank_id",nullable = false)
	private PowerBank powerBank;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public PowerBank getPowerBank() {
		return powerBank;
	}

	public void setPowerBank(PowerBank powerBank) {
		this.powerBank = powerBank;
	}
	
	
	
	

}
