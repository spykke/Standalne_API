package com.spykke.internal.powerbank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spykke.internal.commondb.entity.PowerBank;


@Service
public class PowerBankServiceImpl {

	@Autowired
	PowerBankJpaRepository powerBankJpaRepository;
	
	public Page<PowerBank> findAllPageable(int pageNumber, String sortField, String sortDir){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc")? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNumber-1, 10, sort);
		return powerBankJpaRepository.findAll(pageable);
	}
	
	public List<PowerBank> findAll(){
		return powerBankJpaRepository.findAll();
	}
	
	public PowerBank findById(long id) {
		return powerBankJpaRepository.getOne(id);
	}
	
	public PowerBank save(PowerBank powerBank) {
		return powerBankJpaRepository.save(powerBank);
	}
	
	public PowerBank deleteById(long id) {
		PowerBank powerBank = powerBankJpaRepository.getOne(id);
		powerBank.setIsDeleted(true);
		return save(powerBank);
	}
}
