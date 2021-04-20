package com.spykke.subscribe.banner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.subscribe.banner.entity.Client;

@Repository
public interface ClientJpaRepository extends JpaRepository<Client, Long> {

	public List<Client> findAllByisDeletedFalse();
	
}
