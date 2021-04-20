package com.spykke.internal.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spykke.internal.commondb.entity.Merchant;
import com.spykke.internal.commondb.entity.Order;

@Service
public class MerchantServiceImpl {
	
	@Autowired
	MerchantJpaRepository merchantJpaRepository;
	
	public Page<Merchant> findAllPageable(int pageNumber, String sortField, String sortDir){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc")? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNumber-1, 10, sort);
		return merchantJpaRepository.findAll(pageable);
	}
	
	public List<Merchant> findAll(){
		return merchantJpaRepository.findAll();
	}
	
	public Merchant findById(long id) {
		return merchantJpaRepository.getOne(id);
	}
	
	public Merchant save(Merchant Merchant) {
		return merchantJpaRepository.save(Merchant);
	}
	
	public Merchant deleteById(long id) {
		Merchant Merchant = merchantJpaRepository.getOne(id);
		Merchant.setIsDeleted(true);
		return save(Merchant);
	}

}
