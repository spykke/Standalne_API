package com.spykke.internal.merchant;

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

import com.spykke.internal.commondb.entity.Merchant;


@RestController
public class MerchantController {
	
	@Autowired
	MerchantServiceImpl merchantService;

	@GetMapping("/merchants")
	public List<Merchant> getAllMerchants(){
		return merchantService.findAll();
	}
	
	@GetMapping("/merchants/id/{id}")
	public Merchant getMerchant(@PathVariable long id){
		return merchantService.findById(id);
	}
	
	@PostMapping("/merchants/")
	public ResponseEntity<Void> createMerchant(@RequestBody Merchant merchant){
		Merchant createdMerchant = merchantService.save(merchant);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdMerchant.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/merchants/id/{id}")
	public ResponseEntity<Merchant> updateMerchant(@PathVariable long id,
			@RequestBody Merchant merchant){
		Merchant updatedMerchant = merchantService.save(merchant);
		
		return new ResponseEntity<Merchant>(updatedMerchant, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/merchants/id/{id}")
	public ResponseEntity<Void> deleteMerchant(@PathVariable long id){
		merchantService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
