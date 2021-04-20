package com.spykke.cabinet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.cabinet.entity.Shop;


@Repository
public interface ShopJpaRepository extends JpaRepository<Shop, Long> {

	public List<Shop> findByMerchantIdAndIsDeletedFalse(Long merchantId);
	
	public List<Shop> findByMerchantIdAndIsDeletedFalseOrderByShopNameAsc(Long merchantId);

	public Shop findByShopNameIgnoreCaseContaining(String shop);

	public List<Shop> findAllByShopNameIgnoreCaseContaining(String shop);
	
	public List<Shop> findAllByCityIgnoreCaseContaining(String city);
	
}
