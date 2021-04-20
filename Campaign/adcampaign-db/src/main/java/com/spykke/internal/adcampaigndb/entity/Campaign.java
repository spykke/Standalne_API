package com.spykke.internal.adcampaigndb.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spykke.internal.commondb.entity.PowerBank;

@Entity
@Component
public class Campaign implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String name;
	
	@ManyToOne
	@JoinColumn(name="client_id",nullable = false)
	private Client client;
	
	@Column
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startDate;
	
	@Column
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date endDate;
	
	@Column
	private int powerBankCount;
	
	@Column
	private float amount;
	
	@ManyToOne
	@JoinColumn(name="status_id",nullable = false)
	private Status status; 
	
	@Column
	@JsonIgnore
	private Boolean isDeleted; 
	
	@Transient
	private List<Location> locations;
	
	@Transient
	private List<PowerBank> powerBanks;
	
	
	@Transient
	private List<CampaignLocation> campaignLocations;
	
	@Transient
	private List<CampaignPowerBank> campaignPowerBanks;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPowerBankCount() {
		return powerBankCount;
	}

	public void setPowerBankCount(int powerBankCount) {
		this.powerBankCount = powerBankCount;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public List<PowerBank> getPowerBanks() {
		return powerBanks;
	}

	public void setPowerBanks(List<PowerBank> powerBanks) {
		this.powerBanks = powerBanks;
	}

	public List<CampaignLocation> getCampaignLocations() {
		return campaignLocations;
	}

	public void setCampaignLocations(List<CampaignLocation> campaignLocations) {
		this.campaignLocations = campaignLocations;
	}

	public List<CampaignPowerBank> getCampaignPowerBanks() {
		return campaignPowerBanks;
	}

	public void setCampaignPowerBanks(List<CampaignPowerBank> campaignPowerBanks) {
		this.campaignPowerBanks = campaignPowerBanks;
	}


}
