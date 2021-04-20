package com.spykke.internal.adcampaign.campaignlocation;

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

import com.spykke.internal.adcampaigndb.entity.CampaignLocation;

@RestController
public class CampaignLocationController {
	
	@Autowired
	CampaignLocationServiceImpl campaignLocationService;

	@GetMapping("/campaignLocations")
	public List<CampaignLocation> getAllCampaignLocations(){
		return campaignLocationService.findAll();
	}
	
	@GetMapping("/campaignLocations/id/{id}")
	public CampaignLocation getCampaignLocation(@PathVariable long id){
		return campaignLocationService.findById(id);
	}
	
	@PostMapping("/campaignLocations/")
	public ResponseEntity<Void> createCampaignLocation(@RequestBody CampaignLocation campaignLocation){
		CampaignLocation createdCampaignLocation = campaignLocationService.save(campaignLocation);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdCampaignLocation.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/campaignLocations/id/{id}")
	public ResponseEntity<CampaignLocation> updateCampaignLocation(@PathVariable long id,
			@RequestBody CampaignLocation campaignLocation){
		CampaignLocation updatedCampaignLocation = campaignLocationService.save(campaignLocation);
		
		return new ResponseEntity<CampaignLocation>(updatedCampaignLocation, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/campaignLocations/id/{id}")
	public ResponseEntity<Void> deleteCampaignLocation(@PathVariable long id){
		campaignLocationService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
