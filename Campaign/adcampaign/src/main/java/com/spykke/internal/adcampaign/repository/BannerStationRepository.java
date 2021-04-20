package com.spykke.internal.adcampaign.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.adcampaign.entity.BannerAd;
import com.spykke.internal.adcampaign.entity.BannerStation;

@Repository
public interface BannerStationRepository  extends JpaRepository<BannerStation, Long>{

	List<BannerStation> findByBannerId(long id);

	BannerStation findByStationId(String string);


}
