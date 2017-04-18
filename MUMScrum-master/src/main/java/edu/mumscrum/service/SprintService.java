package edu.mumscrum.service;

import java.util.List;

import edu.mumscrum.domain.Sprint;

public interface SprintService {
	public List<Sprint> findAll();
	public Sprint save(Sprint r);
	public Sprint getSprint(long id);
}
