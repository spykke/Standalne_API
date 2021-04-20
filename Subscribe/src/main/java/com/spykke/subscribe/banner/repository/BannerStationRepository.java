package com.spykke.subscribe.banner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.subscribe.banner.entity.BannerStation;

@Repository
public interface BannerStationRepository  extends JpaRepository<BannerStation, Long>{

	List<BannerStation> findByBannerId(long id);

	BannerStation findByStationId(String string);


}
