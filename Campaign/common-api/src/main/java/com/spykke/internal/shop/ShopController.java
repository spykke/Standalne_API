package com.spykke.internal.shop;

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

import com.spykke.internal.commondb.entity.Shop;

@RestController
public class ShopController {
	
	@Autowired
	ShopServiceImpl shopService;

	@GetMapping("/shops")
	public List<Shop> getAllShops(){
		return shopService.findAll();
	}
	
	@GetMapping("/shops/id/{id}")
	public Shop getShop(@PathVariable long id){
		return shopService.findById(id);
	}
	
	@PostMapping("/shops/")
	public ResponseEntity<Void> createShop(@RequestBody Shop shop){
		Shop createdShop = shopService.save(shop);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdShop.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/shops/id/{id}")
	public ResponseEntity<Shop> updateShop(@PathVariable long id,
			@RequestBody Shop shop){
		Shop updatedShop = shopService.save(shop);
		
		return new ResponseEntity<Shop>(updatedShop, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/shops/id/{id}")
	public ResponseEntity<Void> deleteShop(@PathVariable long id){
		shopService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
