package com.spykke.internal.adcampaigndb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.adcampaigndb.entity.Client;

@Repository
public interface ClientJpaRepository extends JpaRepository<Client, Long> {

	public List<Client> findAllByisDeletedFalse();

	public List<Client> findAllByType(String type);
	
}
