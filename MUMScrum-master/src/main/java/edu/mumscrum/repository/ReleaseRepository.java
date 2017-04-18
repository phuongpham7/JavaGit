package edu.mumscrum.repository;

import org.springframework.data.repository.CrudRepository;

import edu.mumscrum.domain.ReleaseBacklog;

public interface ReleaseRepository extends CrudRepository<ReleaseBacklog, Long> {
		
}
