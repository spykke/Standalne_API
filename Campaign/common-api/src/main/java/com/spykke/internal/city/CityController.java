package com.spykke.internal.city;

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

import com.spykke.internal.commondb.entity.City;

@RestController
public class CityController {
	
	@Autowired
	CityServiceImpl cityService;

	@GetMapping("/citys")
	public List<City> getAllCitys(){
		return cityService.findAll();
	}
	
	@GetMapping("/citys/id/{id}")
	public City getCity(@PathVariable long id){
		return cityService.findById(id);
	}
	
	@PostMapping("/citys/")
	public ResponseEntity<Void> createCity(@RequestBody City city){
		City createdCity = cityService.save(city);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdCity.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/citys/id/{id}")
	public ResponseEntity<City> updateCity(@PathVariable long id,
			@RequestBody City city){
		City updatedCity = cityService.save(city);
		
		return new ResponseEntity<City>(updatedCity, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/citys/id/{id}")
	public ResponseEntity<Void> deleteCity(@PathVariable long id){
		cityService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping("citys/findById")
	@ResponseBody
	public City findById(long id) {
		City city = cityService.findById(id);
 	  return city;
	}	
	
	
	@RequestMapping("citys/findByStateId")
	@ResponseBody
	public List<City> findByStateId(long stateId) {
		List<City> city = cityService.findByStateId(stateId);
 	  return city;
	}	
	
}
