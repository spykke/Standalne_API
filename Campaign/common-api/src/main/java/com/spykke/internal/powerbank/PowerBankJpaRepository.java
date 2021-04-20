package com.spykke.internal.powerbank;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.commondb.entity.PowerBank;

@Repository
public interface PowerBankJpaRepository extends JpaRepository<PowerBank, Long> {
	
	List<PowerBank> findTop10ByOrderByReturnTimeDesc();
	List<PowerBank> findByPowerBankNoIn(List<String> powerBankIds);

}
