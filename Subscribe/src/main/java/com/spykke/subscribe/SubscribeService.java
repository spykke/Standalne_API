package com.spykke.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscribeService {
	
	@Autowired
	SubscribeRepository subscribeRepository;
	
	public SubscribeEntity save(SubscribeEntity subscribe) {
		return subscribeRepository.save(subscribe);
	}

}
