package com.spykke.internal.adcampaign.campaignlocation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spykke.internal.adcampaigndb.entity.CampaignLocation;
import com.spykke.internal.adcampaigndb.repository.CampaignLocationJpaRepository;



@Service
public class CampaignLocationServiceImpl {

	@Autowired
	CampaignLocationJpaRepository campaignLocationJpaRepository;
	
	public List<CampaignLocation> findAll(){
		return campaignLocationJpaRepository.findAll();
	}
	
	public CampaignLocation findById(long id) {
		return campaignLocationJpaRepository.getOne(id);
	}
	
	public CampaignLocation save(CampaignLocation campaignLocation) {
		return campaignLocationJpaRepository.save(campaignLocation);
	}
	
	public CampaignLocation deleteById(long id) {
		CampaignLocation campaignLocation = campaignLocationJpaRepository.getOne(id);
		//campaignLocation.setIsDeleted(true);
		return save(campaignLocation);
	}
}
