package com.spykke.internal.role;

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

import com.spykke.internal.commondb.entity.Role;

@RestController
public class RoleController {
	
	@Autowired
	RoleServiceImpl roleService;

	@GetMapping("/roles")
	public List<Role> getAllRoles(){
		return roleService.findAll();
	}
	
	@GetMapping("/roles/id/{id}")
	public Role getRole(@PathVariable long id){
		return roleService.findById(id);
	}
	
	@PostMapping("/roles/")
	public ResponseEntity<Void> createRole(@RequestBody Role role){
		Role createdRole = roleService.save(role);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdRole.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/roles/id/{id}")
	public ResponseEntity<Role> updateRole(@PathVariable long id,
			@RequestBody Role role){
		Role updatedRole = roleService.save(role);
		
		return new ResponseEntity<Role>(updatedRole, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/roles/id/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable long id){
		roleService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
