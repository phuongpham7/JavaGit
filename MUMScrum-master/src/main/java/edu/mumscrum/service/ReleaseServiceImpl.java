package edu.mumscrum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.mumscrum.domain.ReleaseBacklog;
import edu.mumscrum.repository.ReleaseRepository;

@Component("releaseService")
@Transactional
public class ReleaseServiceImpl implements ReleaseService {

	@Autowired
	private ReleaseRepository releaseRepository;


	public ReleaseServiceImpl(ReleaseRepository releaseRepository) {
		super();
		this.releaseRepository = releaseRepository;
	}


	@Override
	public List<ReleaseBacklog> findAll() {
		// TODO Auto-generated method stub
		return (List<ReleaseBacklog>)releaseRepository.findAll();
	}


	@Override
	public ReleaseBacklog save(ReleaseBacklog us) {
		return releaseRepository.save(us);
	}


	@Override
	public ReleaseBacklog getRelease(long id) {
		// TODO Auto-generated method stub
		return releaseRepository.findOne(id);
	}


}
