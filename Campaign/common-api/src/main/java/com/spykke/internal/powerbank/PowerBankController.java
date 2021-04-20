package com.spykke.internal.powerbank;

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

import com.spykke.internal.commondb.entity.PowerBank;

@RestController
public class PowerBankController {
	
	@Autowired
	PowerBankServiceImpl powerBankService;

	@GetMapping("/powerBanks")
	public List<PowerBank> getAllPowerBanks(){
		return powerBankService.findAll();
	}
	
	@GetMapping("/powerBanks/id/{id}")
	public PowerBank getPowerBank(@PathVariable long id){
		return powerBankService.findById(id);
	}
	
	@PostMapping("/powerBanks/")
	public ResponseEntity<Void> createPowerBank(@RequestBody PowerBank powerBank){
		PowerBank createdPowerBank = powerBankService.save(powerBank);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdPowerBank.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/powerBanks/id/{id}")
	public ResponseEntity<PowerBank> updatePowerBank(@PathVariable long id,
			@RequestBody PowerBank powerBank){
		PowerBank updatedPowerBank = powerBankService.save(powerBank);
		
		return new ResponseEntity<PowerBank>(updatedPowerBank, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/powerBanks/id/{id}")
	public ResponseEntity<Void> deletePowerBank(@PathVariable long id){
		powerBankService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
