package edu.mumscrum.hrSubSystem;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import edu.mumscrum.domain.Employee;
import edu.mumscrum.domain.Role;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findByName(String name);
	Employee findByEmail(String email);


	//@Query("select e from Employee")
	List<Employee> findAll();
	@Query(value="select * from employee e join "
			+ "employee_role r on e.id=r.employee_id where role_name in('TESTER', 'DEVELOPER')",
			nativeQuery=true)
	List<Employee> findDeveloperAndTester();
	
	@Query(value="select * from employee e join "
			+ "employee_role r on e.id=r.employee_id where role_name in('DEVELOPER')",
			nativeQuery=true)
	List<Employee> findAllDevelopers();
	
	@Query(value="select * from employee e join "
			+ "employee_role r on e.id=r.employee_id where role_name in('TESTER')",
			nativeQuery=true)
	List<Employee> findAllTesters();

}
