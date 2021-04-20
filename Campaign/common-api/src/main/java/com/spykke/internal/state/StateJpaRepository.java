package com.spykke.internal.state;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spykke.internal.commondb.entity.State;


@Repository
public interface StateJpaRepository extends JpaRepository<State, Long> {
	
	public List<State> findAllByisDeletedFalse();

	
}
