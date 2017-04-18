package edu.mumscrum.service;

import java.util.List;

import edu.mumscrum.domain.Employee;
import edu.mumscrum.domain.UserStory;

public interface UserStoryService {
	public List<UserStory> findAll();
	public UserStory save(UserStory us);
	public UserStory getUserStory(long id);
	public boolean IsAbleToAssign(long id);
	public List<UserStory> findUserStoriesNotInReleases();
	public List<UserStory> findByDeveloper(Employee developer);
	public List<UserStory> findByTesterId(long id);
	public void delete(long id);
}
