package com.spykke.internal.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spykke.internal.commondb.entity.User;


@Service
public class UserServiceImpl {

	@Autowired
	UserJpaRepository userJpaRepository;
	
	public Page<User> findAllPageable(int pageNumber, String sortField, String sortDir){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc")? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNumber-1, 10, sort);
		return userJpaRepository.findAll(pageable);
	}
	
	public List<User> findAll(){
		return userJpaRepository.findAll();
	}
	
	public User findById(long id) {
		return userJpaRepository.getOne(id);
	}
	
	public User save(User user) {
		return userJpaRepository.save(user);
	}
	
	public User deleteById(long id) {
		User user = userJpaRepository.getOne(id);
		user.setIsDeleted(true);
		return save(user);
	}
}
