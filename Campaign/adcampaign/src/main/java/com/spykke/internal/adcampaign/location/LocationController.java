package com.spykke.internal.adcampaign.location;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spykke.internal.adcampaigndb.entity.Location;

@RestController
public class LocationController {
	
	@Autowired
	LocationServiceImpl locationService;

	@GetMapping("/locations")
	public List<Location> getAllLocations(){
		return locationService.findAll();
	}
	
	@GetMapping("/locations/id/{id}")
	public Location getLocation(@PathVariable long id){
		return locationService.findById(id);
	}
	
	@PostMapping("/locations/")
	public ResponseEntity<Void> createLocation(@RequestBody Location location){
		Location createdLocation = locationService.save(location);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdLocation.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/locations/id/{id}")
	public ResponseEntity<Location> updateLocation(@PathVariable long id,
			@RequestBody Location location){
		Location updatedLocation = locationService.save(location);
		
		return new ResponseEntity<Location>(updatedLocation, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/locations/id/{id}")
	public ResponseEntity<Void> deleteLocation(@PathVariable long id){
		locationService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping("locations/findById")
	@ResponseBody
	public Location findById(long id) {
		Location location = locationService.findById(id);
 	  return location;
	}	
}
