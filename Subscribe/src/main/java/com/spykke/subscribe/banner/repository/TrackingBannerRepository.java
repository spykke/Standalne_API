package com.spykke.subscribe.banner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.subscribe.banner.entity.TrackingBannerAd;

@Repository
public interface TrackingBannerRepository  extends JpaRepository<TrackingBannerAd, Long>{


}
