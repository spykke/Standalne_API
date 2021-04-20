package com.spykke.internal.adcampaign.campaignpowerbank;

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

import com.spykke.internal.adcampaigndb.entity.CampaignPowerBank;

@RestController
public class CampaignPowerBankController {
	
	@Autowired
	CampaignPowerBankServiceImpl campaignPowerBankService;

	@GetMapping("/campaignPowerBanks")
	public List<CampaignPowerBank> getAllCampaignPowerBanks(){
		return campaignPowerBankService.findAll();
	}
	
	@GetMapping("/campaignPowerBanks/id/{id}")
	public CampaignPowerBank getCampaignPowerBank(@PathVariable long id){
		return campaignPowerBankService.findById(id);
	}
	
	@PostMapping("/campaignPowerBanks/")
	public ResponseEntity<Void> createCampaignPowerBank(@RequestBody CampaignPowerBank campaignPowerBank){
		CampaignPowerBank createdCampaignPowerBank = campaignPowerBankService.save(campaignPowerBank);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdCampaignPowerBank.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/campaignPowerBanks/id/{id}")
	public ResponseEntity<CampaignPowerBank> updateCampaignPowerBank(@PathVariable long id,
			@RequestBody CampaignPowerBank campaignPowerBank){
		CampaignPowerBank updatedCampaignPowerBank = campaignPowerBankService.save(campaignPowerBank);
		
		return new ResponseEntity<CampaignPowerBank>(updatedCampaignPowerBank, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/campaignPowerBanks/id/{id}")
	public ResponseEntity<Void> deleteCampaignPowerBank(@PathVariable long id){
		campaignPowerBankService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
