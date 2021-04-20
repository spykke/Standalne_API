package com.spykke.internal.userpackage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spykke.internal.commondb.entity.UserPackage;


@Service
public class UserPackageServiceImpl {

	@Autowired
	UserPackageJpaRepository userPackageJpaRepository;
	
	public Page<UserPackage> findAllPageable(int pageNumber, String sortField, String sortDir){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc")? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNumber-1, 10, sort);
		return userPackageJpaRepository.findAll(pageable);
	}
	
	public List<UserPackage> findAll(){
		return userPackageJpaRepository.findAll();
	}
	
	public UserPackage findById(long id) {
		return userPackageJpaRepository.getOne(id);
	}
	
	public UserPackage save(UserPackage userPackage) {
		return userPackageJpaRepository.save(userPackage);
	}
	
	public UserPackage deleteById(long id) {
		UserPackage userPackage = userPackageJpaRepository.getOne(id);
		userPackage.setIsDeleted(true);
		return save(userPackage);
	}
}
