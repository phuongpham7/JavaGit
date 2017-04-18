package edu.mumscrum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.mumscrum.domain.Employee;
import edu.mumscrum.domain.UserStory;

public interface UserStoryRepository extends CrudRepository<UserStory, Long> {
	
	@Query(value="select * from user_story us where us.id not in (select user_stories_id from release_backlog_user_stories)",
			nativeQuery=true)
	List<UserStory> findUserStoriesNotInReleases();
	
	List<UserStory> findByDeveloper(Employee developer);
	List<UserStory> findByTesterId(long id);	
}
