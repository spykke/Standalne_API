package com.spykke.internal.merchant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.commondb.entity.Merchant;

@Repository
public interface MerchantJpaRepository extends JpaRepository<Merchant, Long> {
	
	List<Merchant> findAllByOrderByNameAsc();

}
