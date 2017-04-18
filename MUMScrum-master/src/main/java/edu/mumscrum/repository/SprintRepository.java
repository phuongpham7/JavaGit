package edu.mumscrum.repository;

import org.springframework.data.repository.CrudRepository;

import edu.mumscrum.domain.Sprint;

public interface SprintRepository extends CrudRepository<Sprint, Long> {
		
}
