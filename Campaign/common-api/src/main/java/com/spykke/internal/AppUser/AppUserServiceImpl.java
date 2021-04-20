package com.spykke.internal.AppUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spykke.internal.commondb.entity.AppUser;


@Service
public class AppUserServiceImpl {

	@Autowired
	AppUserJpaRepository userJpaRepository;
	
	public Page<AppUser> findAllPageable(int pageNumber, String sortField, String sortDir){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc")? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNumber-1, 10, sort);
		return userJpaRepository.findAll(pageable);
	}
	
	public List<AppUser> findAll(){
		return userJpaRepository.findAll();
	}
	
	public AppUser findById(long id) {
		return userJpaRepository.getOne(id);
	}
	
	public AppUser save(AppUser user) {
		return userJpaRepository.save(user);
	}
	
	public AppUser deleteById(long id) {
		AppUser user = userJpaRepository.getOne(id);
		//user.setIsDeleted(true);
		return save(user);
	}
}
