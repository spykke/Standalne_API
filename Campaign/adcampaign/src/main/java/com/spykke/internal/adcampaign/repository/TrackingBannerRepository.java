package com.spykke.internal.adcampaign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.adcampaign.entity.TrackingBannerAd;

@Repository
public interface TrackingBannerRepository  extends JpaRepository<TrackingBannerAd, Long>{


}
