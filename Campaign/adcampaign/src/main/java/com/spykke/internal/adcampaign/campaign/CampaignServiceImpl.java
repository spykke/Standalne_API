package com.spykke.internal.adcampaign.campaign;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spykke.internal.adcampaigndb.entity.Campaign;


@Service
public class CampaignServiceImpl {

	@Autowired
	com.spykke.internal.adcampaigndb.repository.CampaignJpaRepository campaignJpaRepository;
	
	public List<Campaign> findAll(){
		return campaignJpaRepository.findAll();
	}
	
	public Campaign findById(long id) {
		return campaignJpaRepository.getOne(id);
	}
	
	public Campaign save(Campaign campaign) {
		return campaignJpaRepository.save(campaign);
	}
	
	public Campaign deleteById(long id) {
		Campaign campaign = campaignJpaRepository.getOne(id);
		campaign.setIsDeleted(true);
		return save(campaign);
	}
	
	public List<Campaign> findActiveAll(){
		Date today = new Date(); 
		return campaignJpaRepository.findAllByStartDateBeforeAndEndDateAfter(today, today);
	}
	
	public List<Campaign> findFutureAll(){
		Date today = new Date(); 
		return campaignJpaRepository.findAllByStartDateAfter(today);
	}
	
	public List<Campaign> findPastAll(){
		Date today = new Date(); 
		return campaignJpaRepository.findAllByEndDateBefore(today);
	}
	
	public List<Campaign> findAllByStatus(long statusId){
		return campaignJpaRepository.findAllByStatusId(statusId);
	}
	
	public List<Campaign> findAllByClient(long clientId){
		return campaignJpaRepository.findAllByClientId(clientId);
	}
}
