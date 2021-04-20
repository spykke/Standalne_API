package com.spykke.internal.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spykke.internal.commondb.entity.Order;


@Service
public class OrderServiceImpl {

	@Autowired
	OrderJpaRepository orderJpaRepository;
	
	public Page<Order> findAllPageable(int pageNumber, String sortField, String sortDir){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc")? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNumber-1, 10, sort);
		return orderJpaRepository.findAll(pageable);
	}
	
	public List<Order> findAll(){
		return orderJpaRepository.findAll();
	}
	
	public Order findById(long id) {
		return orderJpaRepository.getOne(id);
	}
	
	public Order save(Order order) {
		return orderJpaRepository.save(order);
	}
	
	public Order deleteById(long id) {
		Order order = orderJpaRepository.getOne(id);
		order.setIsDeleted(true);
		return save(order);
	}
}
