package com.spykke.internal.adcampaign.campaignpowerbank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spykke.internal.adcampaigndb.entity.CampaignPowerBank;
import com.spykke.internal.adcampaigndb.repository.CampaignPowerBankJpaRepository;



@Service
public class CampaignPowerBankServiceImpl {

	@Autowired
	CampaignPowerBankJpaRepository campaignPowerBankJpaRepository;
	
	public List<CampaignPowerBank> findAll(){
		return campaignPowerBankJpaRepository.findAll();
	}
	
	public CampaignPowerBank findById(long id) {
		return campaignPowerBankJpaRepository.getOne(id);
	}
	
	public CampaignPowerBank save(CampaignPowerBank campaignPowerBank) {
		return campaignPowerBankJpaRepository.save(campaignPowerBank);
	}
	
	public CampaignPowerBank deleteById(long id) {
		CampaignPowerBank campaignPowerBank = campaignPowerBankJpaRepository.getOne(id);
		//campaignPowerBank.setIsDeleted(true);
		return save(campaignPowerBank);
	}
}
