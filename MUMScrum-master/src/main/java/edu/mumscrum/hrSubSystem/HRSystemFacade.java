package edu.mumscrum.hrSubSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.mumscrum.domain.Employee;

@Component("HRSystemFacade")
@Transactional
public class HRSystemFacade implements IHRSubsystem {

	private final EmployeeService employeeService;

	public HRSystemFacade(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@Override
	public List<Employee> findByName(String name) {
		// TODO Auto-generated method stub
		return employeeService.findByName(name);
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeService.findAll();
	}

	@Override
	public Employee findByEmail(String email) {
		// TODO Auto-generated method stub
		return employeeService.findByEmail(email);
	}
	
	@Override
    public Employee save(Employee empl) {
        return employeeService.save(empl);
    }

	@Override
	public List<Employee> findDeveloperAndTester() {
		// TODO Auto-generated method stub
		return employeeService.findDeveloperAndTester();
	}

	@Override
	public Employee findById(long id) {
		// TODO Auto-generated method stub
		return employeeService.findById(id);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		employeeService.delete(id);
	}

}
