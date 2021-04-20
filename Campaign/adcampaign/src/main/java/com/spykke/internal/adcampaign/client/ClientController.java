package com.spykke.internal.adcampaign.client;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spykke.internal.adcampaigndb.entity.Client;

@RestController
public class ClientController {
	
	@Autowired
	ClientServiceImpl clientService;

	@GetMapping("/clients")
	public List<Client> getAllClients(){
		return clientService.findAll();
	}
	
	@GetMapping("/clients/id/{id}")
	public Client getClient(@PathVariable long id){
		return clientService.findById(id);
	}
	
	@PostMapping("/clients/")
	public ResponseEntity<Void> createClient(@RequestBody Client client){
		Client createdClient = clientService.save(client);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdClient.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/clients/id/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable long id,
			@RequestBody Client client){
		Client updatedClient = clientService.save(client);
		
		return new ResponseEntity<Client>(updatedClient, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/clients/id/{id}")
	public ResponseEntity<Void> deleteClient(@PathVariable long id){
		clientService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping("client/findById")
	@ResponseBody
	public Client findById(long id) {
		Client client = clientService.findById(id);
 	  return client;
	}	
}
