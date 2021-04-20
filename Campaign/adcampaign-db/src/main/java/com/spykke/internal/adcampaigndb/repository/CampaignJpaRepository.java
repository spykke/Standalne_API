package com.spykke.internal.adcampaigndb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.adcampaigndb.entity.Campaign;

@Repository
public interface CampaignJpaRepository extends JpaRepository<Campaign, Long> {

	public List<Campaign> findAllByStartDateBeforeAndEndDateAfter(Date today, Date today2);
	
	public List<Campaign> findAllByStartDateAfter(Date today);
	
	public List<Campaign> findAllByEndDateBefore(Date today);
	
	public List<Campaign> findAllByStatusId(long statusId);
	
	public List<Campaign> findAllByClientId(long clientId);
	
	public List<Campaign> findAllByisDeletedFalse();
	

}
