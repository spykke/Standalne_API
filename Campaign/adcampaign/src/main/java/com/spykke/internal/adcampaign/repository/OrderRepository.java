package com.spykke.internal.adcampaign.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spykke.internal.adcampaign.dao.PowerbankCount;
import com.spykke.internal.commondb.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	public List<Order> findByPowerBankNoAndCreateTimeBetween(String powerBankNo, Date startDate, Date endDate);

	public List<Order> findByPowerBankNoInAndCreateTimeBetween(List<String> pbNos, Date startDate, Date endDate);
	
	@Query("SELECT new com.spykke.internal.adcampaign.dao.PowerbankCount(p.powerBankNo, COUNT(p.powerBankNo)) "
			  + "FROM com.spykke.internal.commondb.entity.Order AS p where p.powerBankNo in :powerBankIds and p.createTime BETWEEN :startDate AND :endDate GROUP BY p.powerBankNo")
			List<PowerbankCount> countTotalPowerBankByPowerBankNoInAndCreateTimeBetween(@Param("powerBankIds")List<String> powerBankIds, @Param("startDate")Date startDate, @Param("endDate")Date endDate);
	

}
