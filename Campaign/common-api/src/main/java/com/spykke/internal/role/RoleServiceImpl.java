package com.spykke.internal.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spykke.internal.commondb.entity.Role;


@Service
public class RoleServiceImpl {

	@Autowired
	RoleJpaRepository roleJpaRepository;
	
	public Page<Role> findAllPageable(int pageNumber, String sortField, String sortDir){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc")? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNumber-1, 10, sort);
		return roleJpaRepository.findAll(pageable);
	}
	
	public List<Role> findAll(){
		return roleJpaRepository.findAll();
	}
	
	public Role findById(long id) {
		return roleJpaRepository.getOne(id);
	}
	
	public Role save(Role role) {
		return roleJpaRepository.save(role);
	}
	
	public Role deleteById(long id) {
		Role role = roleJpaRepository.getOne(id);
		role.setIsDeleted(true);
		return save(role);
	}
}
