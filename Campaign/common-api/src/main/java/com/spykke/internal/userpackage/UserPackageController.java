package com.spykke.internal.userpackage;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spykke.internal.commondb.entity.UserPackage;

@RestController
public class UserPackageController {
	
	@Autowired
	UserPackageServiceImpl userPackageService;

	@GetMapping("/userPackages")
	public List<UserPackage> getAllUserPackages(){
		return userPackageService.findAll();
	}
	
	@GetMapping("/userPackages/id/{id}")
	public UserPackage getUserPackage(@PathVariable long id){
		return userPackageService.findById(id);
	}
	
	@PostMapping("/userPackages/")
	public ResponseEntity<Void> createUserPackage(@RequestBody UserPackage userPackage){
		UserPackage createdUserPackage = userPackageService.save(userPackage);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdUserPackage.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/userPackages/id/{id}")
	public ResponseEntity<UserPackage> updateUserPackage(@PathVariable long id,
			@RequestBody UserPackage userPackage){
		UserPackage updatedUserPackage = userPackageService.save(userPackage);
		
		return new ResponseEntity<UserPackage>(updatedUserPackage, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/userPackages/id/{id}")
	public ResponseEntity<Void> deleteUserPackage(@PathVariable long id){
		userPackageService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
