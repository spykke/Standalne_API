package com.spykke.subscribe.banner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.subscribe.banner.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	
	
}
