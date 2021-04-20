package com.spykke.cabinet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.cabinet.entity.Cabinet;
import com.spykke.cabinet.entity.Shop;

@Repository
public interface CabinetJpaRepository extends JpaRepository<Cabinet, Long>{

	Cabinet findByCabinetNo(String cabinetNo);

	List<Cabinet> findAllByShop(Shop shop);

}
