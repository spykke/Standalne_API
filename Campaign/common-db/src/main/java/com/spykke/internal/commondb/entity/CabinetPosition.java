package com.spykke.internal.commondb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="tb_cabinet_position")
public class CabinetPosition implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="cabinet_no")
	private String cabinetNo;
	
	@Column(name = "power_bank_no",nullable = true )
	private String powerBank;

	@Column
	private int position;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCabinetNo() {
		return cabinetNo;
	}

	public void setCabinetNo(String cabinetNo) {
		this.cabinetNo = cabinetNo;
	}

	public String getPowerBank() {
		return powerBank;
	}

	public void setPowerBank(String powerBank) {
		this.powerBank = powerBank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
 
	

}
