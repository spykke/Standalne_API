package com.spykke.internal.adcampaign.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spykke.internal.adcampaigndb.entity.Client;
import com.spykke.internal.adcampaigndb.repository.ClientJpaRepository;

@Service
public class ClientServiceImpl {

	@Autowired
	ClientJpaRepository clientJpaRepository;
	
	public List<Client> findAll(){
		return clientJpaRepository.findAllByisDeletedFalse();
	}
	
	public Client findById(long id) {
		return clientJpaRepository.getOne(id);
	}
	
	public Client save(Client client) {
		return clientJpaRepository.save(client);
	}
	
	public Client deleteById(long id) {
		Client client = clientJpaRepository.getOne(id);
		client.setIsDeleted(true);
		return save(client);
	}

	public List<Client> findByType(String type) {
		// TODO Auto-generated method stub
		return clientJpaRepository.findAllByType(type);
	}

	
}
