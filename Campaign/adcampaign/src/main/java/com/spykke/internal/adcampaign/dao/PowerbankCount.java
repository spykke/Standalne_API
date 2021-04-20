package com.spykke.internal.adcampaign.dao;

public class PowerbankCount {
	
	private String powerbankNo;
	
	private Long total;
	
	public PowerbankCount(String powerbankNo, Long total) {
		super();
		this.powerbankNo = powerbankNo;
		this.total = total;
	}

	public String getPowerbankNo() {
		return powerbankNo;
	}

	public void setPowerbankNo(String powerbankNo) {
		this.powerbankNo = powerbankNo;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	
}
