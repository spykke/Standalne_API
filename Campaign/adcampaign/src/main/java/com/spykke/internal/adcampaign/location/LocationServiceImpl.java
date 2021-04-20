package com.spykke.internal.adcampaign.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spykke.internal.adcampaigndb.entity.Location;
import com.spykke.internal.adcampaigndb.repository.LocationJpaRepository;



@Service
public class LocationServiceImpl {

	@Autowired
	LocationJpaRepository locationJpaRepository;
	
	public List<Location> findAll(){
		return locationJpaRepository.findAll();
	}
	
	public Location findById(long id) {
		return locationJpaRepository.getOne(id);
	}
	
	public Location save(Location location) {
		return locationJpaRepository.save(location);
	}
	
	public Location deleteById(long id) {
		Location location = locationJpaRepository.getOne(id);
		//location.setIsDeleted(true);
		return save(location);
	}
}
