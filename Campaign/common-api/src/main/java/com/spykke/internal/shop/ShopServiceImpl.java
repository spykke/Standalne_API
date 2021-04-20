package com.spykke.internal.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spykke.internal.commondb.entity.Merchant;
import com.spykke.internal.commondb.entity.Shop;


@Service
public class ShopServiceImpl {

	@Autowired
	ShopJpaRepository shopJpaRepository;
	
	public Page<Shop> findAllPageable(int pageNumber, String sortField, String sortDir){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc")? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNumber-1, 10, sort);
		return shopJpaRepository.findAll(pageable);
	}
	
	public List<Shop> findAll(){
		return shopJpaRepository.findAll();
	}
	
	public Shop findById(long id) {
		return shopJpaRepository.getOne(id);
	}
	
	public Shop save(Shop shop) {
		return shopJpaRepository.save(shop);
	}
	
	public Shop deleteById(long id) {
		Shop shop = shopJpaRepository.getOne(id);
		shop.setIsDeleted(true);
		return save(shop);
	}
}
