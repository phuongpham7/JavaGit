package edu.mumscrum.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mumscrum.domain.Employee;
import edu.mumscrum.domain.EmployeeRole;
import edu.mumscrum.domain.Role;
import edu.mumscrum.hrSubSystem.IHRSubsystem;
import edu.mumscrum.service.UserStoryService;

@Controller
@RequestMapping("/employee")
public class UpdateProfileController {

	@Autowired
	private IHRSubsystem hr;
	
	@RequestMapping(path = "/profile", method = RequestMethod.GET)
	public String getProfileForm(Model model, HttpSession session) {
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		} else {
			Employee user = (Employee) session.getAttribute("loggedInUser");
			model.addAttribute("name", user.getName());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("pwd", user.getPwd());
			return "employee/profile";
		}
	}

	@RequestMapping(path = "/profile", method = RequestMethod.POST)
	public String updateProfile(@RequestParam(value = "nameField", required = true) String name,
			@RequestParam(value = "emailField", required = true) String email, 
			@RequestParam(value = "pwdField", required = true) String pwd, 
			Model model, HttpSession session) {
		Employee user = (Employee) session.getAttribute("loggedInUser");
		user.setName(name);
		user.setEmail(email);
		user.setPwd(pwd);
		hr.save(user);
		model.addAttribute("name", user.getName());
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
}
