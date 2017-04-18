package edu.mumscrum.domain;

import javax.persistence.*;

@Entity
public class EmployeeRole {

	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Employee employee;
	@Enumerated(value = EnumType.STRING)
	private Role roleName;
	
	public EmployeeRole(){
		
	}
	public EmployeeRole(Employee employeeId, Role roleName) {
		super();
		this.employee = employeeId;
		this.roleName = roleName;
	}
	public Employee getEmployeeId() {
		return employee;
	}
	public void setEmployeeId(Employee employeeId) {
		this.employee = employeeId;
	}
	public Role getRoleName() {
		return roleName;
	}
	public void setRoleName(Role roleName) {
		this.roleName = roleName;
	}
	
	
	
	
}
