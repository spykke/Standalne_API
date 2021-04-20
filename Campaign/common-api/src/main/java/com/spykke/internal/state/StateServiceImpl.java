package com.spykke.internal.state;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.spykke.internal.commondb.entity.State;



@Service
public class StateServiceImpl {

	@Autowired
	StateJpaRepository stateJpaRepository;
	
	public Page<State> findAllPageable(int pageNumber, String sortField, String sortDir){
		Sort sort = Sort.by(sortField);
		sort = sortDir.equalsIgnoreCase("asc")? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNumber-1, 10, sort);
		return stateJpaRepository.findAll(pageable);
	}
	
	public List<State> findAll(){
		return stateJpaRepository.findAllByisDeletedFalse();
	}
	
	public State findById(long id) {
		return stateJpaRepository.getOne(id);
	}
	
	public Optional<State> findByIdOptional(long id) {
		return stateJpaRepository.findById(id);
	}
	
	public State save(State state) {
		return stateJpaRepository.save(state);
	}
	
	public State deleteById(long id) {
		State state = stateJpaRepository.getOne(id);
		state.setIsDeleted(true);
		return save(state);
	}
}
