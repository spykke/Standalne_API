package com.spykke.internal.adcampaign.campaign;

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

import com.spykke.internal.adcampaigndb.entity.Campaign;

@RestController
public class CampaignController {
	
	@Autowired
	CampaignServiceImpl campaignService;

	@GetMapping("/campaigns")
	public List<Campaign> getAllCampaigns(){
		return campaignService.findAll();
	}
	
	@GetMapping("/campaigns/id/{id}")
	public Campaign getCampaign(@PathVariable long id){
		return campaignService.findById(id);
	}
	
	@PostMapping("/campaigns/")
	public ResponseEntity<Void> createCampaign(@RequestBody Campaign campaign){
		Campaign createdCampaign = campaignService.save(campaign);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdCampaign.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/campaigns/id/{id}")
	public ResponseEntity<Campaign> updateCampaign(@PathVariable long id,
			@RequestBody Campaign campaign){
		Campaign updatedCampaign = campaignService.save(campaign);
		
		return new ResponseEntity<Campaign>(updatedCampaign, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/campaigns/id/{id}")
	public ResponseEntity<Void> deleteCampaign(@PathVariable long id){
		campaignService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/campaigns/status/{statusId}")
	public List<Campaign> getAllCampaignsByStatus(@PathVariable long statusId){
		return campaignService.findAllByStatus(statusId);
	}
	
	@GetMapping("/campaigns/client/{clientId}")
	public List<Campaign> getAllCampaignsByClient(@PathVariable long clientId){
		return campaignService.findAllByClient(clientId);
	}
	
	@GetMapping("/campaigns/active")
	public List<Campaign> getAllActiveCampaigns(){
		return campaignService.findActiveAll();
	}
	
	@GetMapping("/campaigns/past")
	public List<Campaign> getAllPastCampaigns(){
		return campaignService.findPastAll();
	}
	
	@GetMapping("/campaigns/upcoming")
	public List<Campaign> getAllUpcomingCampaigns(){
		return campaignService.findFutureAll();
	}
}
