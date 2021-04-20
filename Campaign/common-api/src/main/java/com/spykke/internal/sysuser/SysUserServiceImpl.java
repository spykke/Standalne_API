package com.spykke.internal.sysuser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spykke.internal.commondb.entity.SysUser;


@Service
public class SysUserServiceImpl {

	@Autowired
	SysUserJpaRepository userJpaRepository;
	
	public Page<SysUser> findAllPageable(int pageNumber, String sortField, String sortDir){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc")? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNumber-1, 10, sort);
		return userJpaRepository.findAll(pageable);
	}
	
	public List<SysUser> findAll(){
		return userJpaRepository.findAll();
	}
	
	public SysUser findById(long id) {
		return userJpaRepository.getOne(id);
	}
	
	public SysUser save(SysUser user) {
		return userJpaRepository.save(user);
	}
	
	public SysUser deleteById(long id) {
		SysUser user = userJpaRepository.getOne(id);
		//user.setIsDeleted(true);
		return save(user);
	}
}
