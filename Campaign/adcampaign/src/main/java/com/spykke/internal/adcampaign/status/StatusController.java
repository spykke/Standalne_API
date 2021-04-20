package com.spykke.internal.adcampaign.status;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.spykke.internal.adcampaigndb.entity.Status;
import com.spykke.internal.commondb.entity.State;

@RestController
public class StatusController {
	
	@Autowired
	StatusServiceImpl statusService;

	@GetMapping("/statuses")
	public List<Status> getAllStatuss(){
		return statusService.findAll();
	}
	
	@GetMapping("/statuses/id/{id}")
	public Status getStatus(@PathVariable long id){
		return statusService.findById(id);
	}
	
	@PostMapping("/statuses/")
	public ResponseEntity<Void> createStatus(@RequestBody Status status){
		Status createdStatus = statusService.save(status);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdStatus.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/statuses/id/{id}")
	public ResponseEntity<Status> updateStatus(@PathVariable long id,
			@RequestBody Status status){
		Status updatedStatus = statusService.save(status);
		
		return new ResponseEntity<Status>(updatedStatus, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/statuses/id/{id}")
	public ResponseEntity<Void> deleteStatus(@PathVariable long id){
		statusService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping("statuses/findById")
	@ResponseBody
	public Status findById(long id) {
		Status status = statusService.findById(id);
		System.out.println("Status "+status);
 	  return status;
	}	
}
