package com.spykke.internal.city;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spykke.internal.commondb.entity.City;
import com.spykke.internal.commondb.entity.Merchant;



@Service
public class CityServiceImpl {

	@Autowired
	CityJpaRepository cityJpaRepository;
	
	public Page<City> findAllPageable(int pageNumber, String sortField, String sortDir){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc")? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNumber-1, 10, sort);
		return cityJpaRepository.findAll(pageable);
	}
	
	public List<City> findAll(){
		return cityJpaRepository.findAllByisDeletedFalse();
	}
	
	public City findById(long id) {
		return cityJpaRepository.getOne(id);
	}
	
	public List<City> findByStateId(long stateId) {
		return cityJpaRepository.findByStateIdAndIsDeletedFalse(stateId);
	}
	
	public City save(City city) {
		return cityJpaRepository.save(city);
	}
	
	public City deleteById(long id) {
		City city = cityJpaRepository.getOne(id);
		city.setIsDeleted(true);
		return save(city);
	}
}
