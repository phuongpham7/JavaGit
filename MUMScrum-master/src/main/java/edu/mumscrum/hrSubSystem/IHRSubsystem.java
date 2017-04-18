package edu.mumscrum.hrSubSystem;

import java.util.List;

import edu.mumscrum.domain.Employee;

public interface IHRSubsystem {		
		List<Employee> findByName(String name);
		Employee findByEmail(String email);
		List<Employee> findAll();

		List<Employee> findDeveloperAndTester();
		Employee findById(long id);

		Employee save(Employee empl);
		void delete(long id);
}
