package com.spykke.internal.adcampaign.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.adcampaign.entity.BannerAd;

@Repository
public interface VideoBannerRepository  extends JpaRepository<BannerAd, Long>{


}
