package com.spykke.internal.order;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.commondb.entity.Order;

@Repository
public interface OrderJpaRepository extends JpaRepository<Order, Long> {
	
	public List<Order> findByPowerBankNoAndCreateTimeBetween(String powerBankNo, Date startDate, Date endDate);

	public List<Order> findByPowerBankNoInAndCreateTimeBetween(List<String> pbNos, Date startDate, Date endDate);

}
