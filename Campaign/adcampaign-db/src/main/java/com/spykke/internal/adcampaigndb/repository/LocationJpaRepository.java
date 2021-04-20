package com.spykke.internal.adcampaigndb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.adcampaigndb.entity.Location;

@Repository
public interface LocationJpaRepository extends JpaRepository<Location, Long> {

}
