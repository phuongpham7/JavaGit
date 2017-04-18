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

@Controller
public class LoginController {
	@Autowired
	private IHRSubsystem hr;
	
	@RequestMapping(value="/login", method= RequestMethod.GET)
	public String getLoginForm(){
		
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "loginEmail", required = true) String email,
			@RequestParam(value = "loginPwd", required = true) String pwd, HttpSession session, Model model) {

		Employee user = hr.findByEmail(email);
		if (user == null) {
			model.addAttribute("validitity", "Invalid email. Try again");
			return "login";
		} else {
			if (user.getPwd().equals(pwd)) {
				session.setAttribute("loggedInUser", user);
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
			} else {
				model.addAttribute("validitity", "Invalid password");
				model.addAttribute("email", email);
				return "login";
			}
		}
	}
	
	@RequestMapping(value = "/employee/main", method= RequestMethod.GET)
	public String getEmployeeMainPage(Model model, HttpSession session){
		if(session.getAttribute("loggedInUser") != null){
			Employee user = (Employee) session.getAttribute("loggedInUser");
			model.addAttribute("user", user);
		}
		return "employee/main";
	}
	
	
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		if(session.getAttribute("loggedInUser") == null){
			return "redirect:/login";
		}
		session.removeAttribute("loggedInUser");
		return "redirect:/login";
	}
	
	
}
