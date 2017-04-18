package edu.mumscrum.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Sprint {
	@Id
	@GeneratedValue
	private long id;
	@NotNull
	private String name;
	@NotNull
	private LocalDate startDate; 
	@NotNull
	private LocalDate dueDate;
	@OneToMany
	@JoinTable(name="UserStory_List")
	private List<UserStory> userStoryList;
	@ManyToOne
	private ReleaseBacklog releaseBacklog;
	@NotNull
	private long scrumMasterId;
	
	public Sprint(){
		
	}

	public Sprint(long id, String name, LocalDate startDate, LocalDate dueDate,
			List<UserStory> userStoryList, ReleaseBacklog releaseBacklog,
			long scrumMasterId) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.userStoryList = userStoryList;
		this.releaseBacklog = releaseBacklog;
		this.scrumMasterId = scrumMasterId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public List<UserStory> getUserStoryList() {
		return userStoryList;
	}

	public void setUserStoryList(List<UserStory> userStoryList) {
		this.userStoryList = userStoryList;
	}

	public ReleaseBacklog getReleaseBacklog() {
		return releaseBacklog;
	}

	public void setReleaseBacklog(ReleaseBacklog releaseBacklog) {
		this.releaseBacklog = releaseBacklog;
	}

	public long getScrumMasterId() {
		return scrumMasterId;
	}

	public void setScrumMasterId(long scrumMasterId) {
		this.scrumMasterId = scrumMasterId;
	}
}