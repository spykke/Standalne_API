package com.spykke.internal.adcampaign.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.commondb.entity.CabinetPosition;

@Repository
public interface CaibnetPositionJpaRepository extends JpaRepository<CabinetPosition, Long> {

	public List<CabinetPosition> findByCabinetNo(String cabinetNo);

	//public List<CabinetPosition> findByShopId(long shopId);
}
