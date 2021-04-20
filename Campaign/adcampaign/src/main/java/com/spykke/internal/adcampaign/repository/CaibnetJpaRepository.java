package com.spykke.internal.adcampaign.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.commondb.entity.Cabinet;

@Repository
public interface CaibnetJpaRepository extends JpaRepository<Cabinet, Long> {

	public List<Cabinet> findByShopId(Long shopId);

	public List<Cabinet> findByShopNotNull();

	//public List<Cabinet> findAllTopTenById();
}
