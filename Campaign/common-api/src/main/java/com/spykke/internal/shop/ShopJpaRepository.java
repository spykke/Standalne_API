package com.spykke.internal.shop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.commondb.entity.Shop;

@Repository
public interface ShopJpaRepository extends JpaRepository<Shop, Long> {

	public List<Shop> findByMerchantIdAndIsDeletedFalse(Long merchantId);
	
	public List<Shop> findByMerchantIdAndIsDeletedFalseOrderByShopNameAsc(Long merchantId);
	
}
