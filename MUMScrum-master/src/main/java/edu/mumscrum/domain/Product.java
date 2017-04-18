package edu.mumscrum.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String description;
	@OneToMany(mappedBy="product",cascade=CascadeType.PERSIST)
	private List<UserStory> userStoryList;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String name, String description, List<UserStory> userStoryList) {
		super();
		this.name = name;
		this.description = description;
		this.userStoryList = userStoryList;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<UserStory> getUserStoryList() {
		return userStoryList;
	}

	public void setUserStoryList(List<UserStory> userStoryList) {
		this.userStoryList = userStoryList;
	}
	

}
