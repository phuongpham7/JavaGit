package edu.mumscrum.domain;

import java.util.List;

import javax.persistence.*;

@Entity
public class UserStory {
	@Id
	@GeneratedValue
	private long id;

	private String title;

//	@OneToOne
//	@JoinColumn(name = "employeeId")
//	private Employee assignedTo;
	
	@OneToOne
	@JoinColumn(name = "developerId")
	private Employee developer;
	
	@OneToOne
	@JoinColumn(name = "testerId")
	private Employee tester;

	@OneToOne
	@JoinColumn(name = "productBacklogId")
	private ProductBackLog productBacklog;

	@Enumerated(value = EnumType.STRING)
	private State state = State.NEW;

	private String description;

	private int estimatedHours;

	@OneToMany(cascade=CascadeType.ALL)
	private List<LogHour> developerLogHour;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<LogHour> testerLogHour;

	@ManyToOne()
	private Product product;

	public UserStory() {
		// TODO Auto-generated constructor stub
	}

	public UserStory(String title, Employee developer, Employee tester, ProductBackLog productBacklog, State state, String description,
			int estimatedHours, List<LogHour> developerLogHour, List<LogHour> testerLogHour, Product product) {
		super();
		this.title = title;
		this.developer = developer;
		this.tester = tester;
		this.productBacklog = productBacklog;
		this.state = state;
		this.description = description;
		this.estimatedHours = estimatedHours;
		this.developerLogHour = developerLogHour;
		this.testerLogHour = testerLogHour;
		this.product = product;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductBackLog getProductBacklog() {
		return productBacklog;
	}

	public void setProductBacklog(ProductBackLog productBacklog) {
		this.productBacklog = productBacklog;
	}

	public int getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(int estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public List<LogHour> getDeveloperLogHour() {
		return developerLogHour;
	}

	public void setDeveloperLogHour(List<LogHour> developerLogHour) {
		this.developerLogHour = developerLogHour;
	}

	public List<LogHour> getTesterLogHour() {
		return testerLogHour;
	}

	public void setTesterLogHour(List<LogHour> testerLogHour) {
		this.testerLogHour = testerLogHour;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Employee getDeveloper() {
		return developer;
	}

	public void setDeveloper(Employee developer) {
		this.developer = developer;
	}

	public Employee getTester() {
		return tester;
	}

	public void setTester(Employee tester) {
		this.tester = tester;
	}
}
