package edu.mumscrum.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mumscrum.domain.Employee;
import edu.mumscrum.domain.EmployeeRole;
import edu.mumscrum.domain.Role;
import edu.mumscrum.hrSubSystem.*;
import edu.mumscrum.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	private IHRSubsystem hr;	
	@Autowired
	private ProductService productService;
	

	@RequestMapping("/")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model, HttpSession session) {
		if(session.getAttribute("loggedInUser") != null){
			Employee user = (Employee) session.getAttribute("loggedInUser");
			for(EmployeeRole roles : user.getRolelist()){
				if(roles.getRoleName() == Role.PRODUCTOWNER){
					model.addAttribute("productOwner", user);						
				}
				if(roles.getRoleName() == Role.SCRUMMASTER){
					model.addAttribute("scrumMaster", user);
				}					
				if(roles.getRoleName() == Role.DEVELOPER){
					model.addAttribute("developer", user);
				}
				if(roles.getRoleName() == Role.TESTER){
					model.addAttribute("tester", user);
				}
				if(roles.getRoleName().equals(Role.HR)){
					model.addAttribute("Hr", user);
				}
			}
			model.addAttribute("user", user);
			return "employee/main";
		}
		
		model.addAttribute("name", name);
		return "home";
	}

	@RequestMapping("/user")
	public String getUser(Model model) {
		
		Employee employees = hr.findAll().get(0);
		//Employee employees2 = employee.
		model.addAttribute("name", employees);
		return "employee/user";
	}
	@RequestMapping(value="/error", method= RequestMethod.GET)
	public String error(){
		
		return "error";
	}

}
