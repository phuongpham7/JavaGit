package edu.mumscrum.service;

import java.util.List;

import edu.mumscrum.domain.ReleaseBacklog;

public interface ReleaseService {
	public List<ReleaseBacklog> findAll();
	public ReleaseBacklog save(ReleaseBacklog r);
	public ReleaseBacklog getRelease(long id);
}
