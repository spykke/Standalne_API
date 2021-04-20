package com.spykke.internal.adcampaigndb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.adcampaigndb.entity.CampaignPowerBank;

@Repository
public interface CampaignPowerBankJpaRepository extends JpaRepository<CampaignPowerBank, Long> {
	List<CampaignPowerBank> findByCampaignId(Long campaignId);
	
}
