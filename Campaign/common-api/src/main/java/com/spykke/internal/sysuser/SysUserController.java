package com.spykke.internal.sysuser;

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

import com.spykke.internal.commondb.entity.SysUser;

@RestController
public class SysUserController {
	
	@Autowired
	SysUserServiceImpl userService;

	@GetMapping("/sysusers")
	public List<SysUser> getAllSysUsers(){
		return userService.findAll();
	}
	
	@GetMapping("/sysusers/id/{id}")
	public SysUser getSysUser(@PathVariable long id){
		return userService.findById(id);
	}
	
	@PostMapping("/sysusers/")
	public ResponseEntity<Void> createSysUser(@RequestBody SysUser user){
		SysUser createdSysUser = userService.save(user);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdSysUser.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/sysusers/id/{id}")
	public ResponseEntity<SysUser> updateSysUser(@PathVariable long id,
			@RequestBody SysUser user){
		SysUser updatedSysUser = userService.save(user);
		
		return new ResponseEntity<SysUser>(updatedSysUser, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/sysusers/id/{id}")
	public ResponseEntity<Void> deleteSysUser(@PathVariable long id){
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
