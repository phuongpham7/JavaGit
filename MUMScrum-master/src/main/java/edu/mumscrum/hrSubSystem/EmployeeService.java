package edu.mumscrum.hrSubSystem;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.mumscrum.domain.Employee;

 public interface EmployeeService {
	List<Employee> findByName(String name);
	Employee findByEmail(String email);
	List<Employee> findAll();

	List<Employee> findDeveloperAndTester();
	Employee save(Employee empl);
	Employee findById(long id);
	List<Employee> findAllDevelopers();
	List<Employee> findAllTesters();
	void delete(long id);
}
