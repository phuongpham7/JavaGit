package edu.mumscrum.hrSubSystem;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.mumscrum.domain.Employee;
import edu.mumscrum.domain.Role;

@Component("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> findByName(String name) {
		
		return employeeRepository.findByName(name);
	}

	@Override
	public List<Employee> findAll() {
		
		return (List<Employee>)employeeRepository.findAll();
	}

	@Override
	public Employee findByEmail(String email) {
	
		return (Employee)employeeRepository.findByEmail(email);
	}
	


	@Override
    public Employee save(Employee empl) {
        return employeeRepository.save(empl);
    }

	@Override
	public Employee findById(long id) {
		// TODO Auto-generated method stub
		return employeeRepository.findOne(id);
	}

	@Override
	public List<Employee> findDeveloperAndTester() {
		return employeeRepository.findDeveloperAndTester();
	}

	@Override
	public List<Employee> findAllDevelopers() {
		return employeeRepository.findAllDevelopers();
	}

	@Override
	public List<Employee> findAllTesters() {
		return employeeRepository.findAllTesters();
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		employeeRepository.delete(id);
	}
}
