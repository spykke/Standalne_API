package com.spykke.internal.adcampaign.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spykke.internal.adcampaign.dao.PowerbankCount;
import com.spykke.internal.commondb.entity.PowerBank;

@Repository
public interface PowerBankRepository extends JpaRepository<PowerBank, Long> {
	
	List<PowerBank> findTop10ByOrderByReturnTimeDesc();
	List<PowerBank> findByPowerBankNoIn(List<String> powerBankIds);
	
	@Query("SELECT new com.spykke.internal.adcampaign.dao.PowerbankCount(p.powerBankNo, COUNT(p.powerBankNo)) "
			  + "FROM com.spykke.internal.commondb.entity.PowerBank AS p where p.powerBankNo in :powerBankIds GROUP BY p.powerBankNo")
			List<PowerbankCount> countTotalPowerBankByPowerBankNoIn(@Param("powerBankIds")List<String> powerBankIds);
	
	PowerBank findByPowerBankNo(String powerBankNo);



}
