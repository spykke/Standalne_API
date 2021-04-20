package com.spykke.internal.AppUser;

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

import com.spykke.internal.commondb.entity.AppUser;

@RestController
public class AppUserController {
	
	@Autowired
	AppUserServiceImpl userService;

	@GetMapping("/appusers")
	public List<AppUser> getAllAppUsers(){
		return userService.findAll();
	}
	
	@GetMapping("/appusers/id/{id}")
	public AppUser getAppUser(@PathVariable long id){
		return userService.findById(id);
	}
	
	@PostMapping("/appusers/")
	public ResponseEntity<Void> createAppUser(@RequestBody AppUser user){
		AppUser createdAppUser = userService.save(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdAppUser.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/appusers/id/{id}")
	public ResponseEntity<AppUser> updateAppUser(@PathVariable long id,
			@RequestBody AppUser user){
		AppUser updatedAppUser = userService.save(user);
		
		return new ResponseEntity<AppUser>(updatedAppUser, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/appusers/id/{id}")
	public ResponseEntity<Void> deleteAppUser(@PathVariable long id){
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
