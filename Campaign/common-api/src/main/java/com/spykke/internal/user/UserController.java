package com.spykke.internal.user;

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

import com.spykke.internal.commondb.entity.User;

@RestController
public class UserController {
	
	@Autowired
	UserServiceImpl userService;

	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.findAll();
	}
	
	@GetMapping("/users/id/{id}")
	public User getUser(@PathVariable long id){
		return userService.findById(id);
	}
	
	@PostMapping("/users/")
	public ResponseEntity<Void> createUser(@RequestBody User user){
		User createdUser = userService.save(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/users/id/{id}")
	public ResponseEntity<User> updateUser(@PathVariable long id,
			@RequestBody User user){
		User updatedUser = userService.save(user);
		
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/users/id/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable long id){
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
