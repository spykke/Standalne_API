package com.spykke.internal.adcampaigndb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.adcampaigndb.entity.Status;

@Repository
public interface StatusJpaRepository extends JpaRepository<Status, Long> {

	public List<Status> findAllByIsDeletedFalse();

}
