package com.spykke.cabinet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.cabinet.entity.Merchant;


@Repository
public interface MerchantJpaRepository extends JpaRepository<Merchant, Long> {
	
	List<Merchant> findAllByOrderByNameAsc();

	Merchant findByNameIgnoreCaseContaining(String merchant);

	List<Merchant> findAllByNameIgnoreCaseContaining(String merchant);

}
