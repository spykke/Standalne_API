package com.spykke.internal.adcampaigndb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.spykke.internal.commondb.entity.City;
import com.spykke.internal.commondb.entity.State;

@Entity
public class Location {
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="state_id",nullable = false)
	private State state;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	private City city;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	

}
