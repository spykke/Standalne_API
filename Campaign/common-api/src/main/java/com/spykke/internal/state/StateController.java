package com.spykke.internal.state;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.spykke.internal.commondb.entity.State;




@RestController
public class StateController {
	
	@Autowired
	StateServiceImpl stateService;

	@GetMapping("/states")
	public List<State> getAllStates(){
		return stateService.findAll();
	}
	
	@GetMapping("/states/id/{id}")
	public State getState(@PathVariable long id){
		return stateService.findById(id);
	}
	
	@PostMapping("/states")
	public ResponseEntity<Void> createState(@RequestBody State state){
		State createdState = stateService.save(state);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdState.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/states/id/{id}")
	public ResponseEntity<State> updateState(@PathVariable long id,
			@RequestBody State state){
		State updatedState = stateService.save(state);
		
		return new ResponseEntity<State>(updatedState, HttpStatus.OK) ;
	}
	
	@DeleteMapping("/states/id/{id}")
	public ResponseEntity<Void> deleteState(@PathVariable long id){
		stateService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping("states/findById")
	@ResponseBody
	public Optional<State> findById(long id) {
		Optional<State> state = stateService.findByIdOptional(id);
		System.out.println("State "+state);
 	  return state;
	}	
}
