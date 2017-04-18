package edu.mumscrum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.mumscrum.domain.Sprint;
import edu.mumscrum.repository.SprintRepository;

@Component("sprintService")
@Transactional
public class SprintServiceImpl implements SprintService {

	@Autowired
	private SprintRepository sprintRepository;


	public SprintServiceImpl(SprintRepository sprintRepository) {
		super();
		this.sprintRepository = sprintRepository;
	}


	@Override
	public List<Sprint> findAll() {
		// TODO Auto-generated method stub
		return (List<Sprint>)sprintRepository.findAll();
	}


	@Override
	public Sprint save(Sprint us) {
		return sprintRepository.save(us);
	}


	@Override
	public Sprint getSprint(long id) {
		// TODO Auto-generated method stub
		return sprintRepository.findOne(id);
	}


}
