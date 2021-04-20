package com.spykke.internal.adcampaign.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.spykke.internal.adcampaigndb.entity.Client;
import com.spykke.internal.adcampaigndb.entity.Location;

@Entity(name="tb_banner_ad")
public class BannerAd  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String adName;
	
	@Column
	private String videoUrl;
	
	@Column
	private String bannerUrl;
	
	@Column
	private String websiteUrl;
	
	@Column
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@Column
	private Boolean isDeleted;
	
//	@ElementCollection
//    @CollectionTable(name = "tb_banner_stations", joinColumns = @JoinColumn(name = "banner_id"))
//    @Column(name = "stationid")
//    private Set<String> stationid = new HashSet<String>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	} 
	
	
	

}
