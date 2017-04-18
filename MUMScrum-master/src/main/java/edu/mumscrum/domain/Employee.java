package edu.mumscrum.domain;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private long id;
	@NotNull
	private String name;
	private String pwd;
	private String email;
	@OneToMany()
	@JoinTable(name="Employee_List")	
	private List<EmployeeRole> rolelist;
	@ManyToMany()
	@JoinTable(name="Employee_Userstory")
	private List<UserStory> userStories;

	public Employee() {
		// TODO Auto-generated constructor stub
	}


	public Employee(String name, String pwd, String email, List<EmployeeRole> rolelist, List<UserStory> userStories) {
		super();
		this.name = name;
		this.pwd = pwd;
		this.email = email;
		this.rolelist = rolelist;
		this.userStories = userStories;
	}


	public long getId() {
		return this.id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public List<EmployeeRole> getRolelist() {
		return rolelist;
	}


	public void setRolelist(List<EmployeeRole> rolelist) {
		this.rolelist = rolelist;
	}
	
	public List<UserStory> getUserStories(){
		return userStories;
	}
	
	public void setUserStories(List<UserStory> userStories){
		this.userStories = userStories;
	}
	
	public void removeUserStory(UserStory userStory){
		userStories.remove(userStory);
	}

}
