package edu.mumscrum.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "release_backlog")
public class ReleaseBacklog {
	
	@Id
	@GeneratedValue
	@Column(name="name")
	private String name;
	@Column(name="id")
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="targetDate")
	private Date targetDate;
	@OneToMany
	private List<UserStory> userStories;
	@OneToMany
	private List<Sprint> sprints;
	@ManyToOne()
	private Product product;
	@ManyToOne
	private Employee scrumMaster;
	
	
	public ReleaseBacklog() {
		// TODO Auto-generated constructor stub
	}
	
	public ReleaseBacklog(String name, Date targetDate, List<UserStory> userStories, List<Sprint> sprints, Product product) {
		super();
		this.name = name;
		this.targetDate = targetDate;
		this.userStories = userStories;
		this.sprints = sprints;
		this.product = product;
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

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	
	public List<UserStory> getUserStories() {
		return userStories;
	}

	public void setUserStories(List<UserStory> userStories) {
		this.userStories = userStories;
	}

	public List<Sprint> getSprints() {
		return sprints;
	}

	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Employee getScrumMaster() {
		return scrumMaster;
	}

	public void setScrumMaster(Employee scrumMaster) {
		this.scrumMaster = scrumMaster;
	}
}
