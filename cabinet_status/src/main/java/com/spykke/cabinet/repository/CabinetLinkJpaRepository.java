package com.spykke.cabinet.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.cabinet.entity.CabinetLink;

@Repository
public interface CabinetLinkJpaRepository extends JpaRepository<CabinetLink, Long>{

	List<CabinetLink> findAllByCabinetNoAndCreateTimeLike(String cabinetNo, String createTime);

	List<CabinetLink> findAllByCabinetNoAndCreateTimeBetween(String cabinetNo, Date time, Date time2);

	List<CabinetLink> findAllByCabinetNoAndCreateTimeBetweenOrderByCreateTimeAsc(String cabinetNo, Date time,
			Date time2);
}
