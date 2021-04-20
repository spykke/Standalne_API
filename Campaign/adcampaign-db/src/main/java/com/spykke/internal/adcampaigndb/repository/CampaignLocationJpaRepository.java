package com.spykke.internal.adcampaigndb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.adcampaigndb.entity.CampaignLocation;

@Repository
public interface CampaignLocationJpaRepository extends JpaRepository<CampaignLocation, Long> {

	List<CampaignLocation> findByCampaignId(long id);

	
}
