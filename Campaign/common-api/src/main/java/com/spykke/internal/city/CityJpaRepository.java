package com.spykke.internal.city;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.commondb.entity.City;


@Repository
public interface CityJpaRepository extends JpaRepository<City, Long> {

	public List<City> findAllByisDeletedFalse();
	public List<City> findByStateIdAndIsDeletedFalse(Long stateId);
	
}
