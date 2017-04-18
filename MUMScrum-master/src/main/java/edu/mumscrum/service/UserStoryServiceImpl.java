package edu.mumscrum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.mumscrum.domain.Employee;
import edu.mumscrum.domain.UserStory;
import edu.mumscrum.repository.UserStoryRepository;

@Component("userStoryService")
@Transactional
public class UserStoryServiceImpl implements UserStoryService {

	@Autowired
	private UserStoryRepository userStoryRepository;


	public UserStoryServiceImpl(UserStoryRepository userStoryRepository) {
		super();
		this.userStoryRepository = userStoryRepository;
	}


	@Override
	public List<UserStory> findAll() {
		// TODO Auto-generated method stub
		return (List<UserStory>)userStoryRepository.findAll();
	}


	@Override
	public UserStory save(UserStory us) {
		return userStoryRepository.save(us);
	}


	@Override
	public UserStory getUserStory(long id) {
		// TODO Auto-generated method stub
		return userStoryRepository.findOne(id);
	}


	@Override
	public boolean IsAbleToAssign(long id) {
		UserStory userStory = getUserStory(id);
		if (userStory.getEstimatedHours() > 0){
			return false;
		}
		return true;
	}


	@Override
	public List<UserStory> findUserStoriesNotInReleases() {
		// TODO Auto-generated method stub
		return userStoryRepository.findUserStoriesNotInReleases();
	}


	@Override
	public List<UserStory> findByDeveloper(Employee developer) {
		// TODO Auto-generated method stub
		return userStoryRepository.findByDeveloper(developer);
	}
	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		 userStoryRepository.delete(id);
	}

	@Override
	public List<UserStory> findByTesterId(long id) {
		// TODO Auto-generated method stub
		return userStoryRepository.findByTesterId(id);
	}

}
