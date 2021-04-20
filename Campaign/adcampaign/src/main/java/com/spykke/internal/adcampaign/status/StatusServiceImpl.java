package com.spykke.internal.adcampaign.status;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spykke.internal.adcampaigndb.entity.Status;
import com.spykke.internal.adcampaigndb.repository.StatusJpaRepository;

@Service
public class StatusServiceImpl {

	@Autowired
	StatusJpaRepository statusJpaRepository;
	
	public List<Status> findAll(){
		return statusJpaRepository.findAllByIsDeletedFalse();
	}
	
	public Status findById(long id) {
		return statusJpaRepository.getOne(id);
	}
	
	public Status save(Status status) {
		return statusJpaRepository.save(status);
	}
	
	public Status deleteById(long id) {
		Status status = statusJpaRepository.getOne(id);
		status.setIsDeleted(true);
		return save(status);
	}
}
