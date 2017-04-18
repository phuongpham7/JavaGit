package edu.mumscrum.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mumscrum.domain.Employee;
import edu.mumscrum.hrSubSystem.HRSystemFacade;

@Controller
public class EmployeeController {
	private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private HRSystemFacade employeeFacade;
	
	// list page
		@RequestMapping(value = "/employee", method = RequestMethod.GET)
		public String showAllUsers(Model model) {

			logger.debug("showAllUsers()");
			model.addAttribute("users", employeeFacade.findAll());
			return "employee/list";

		}
		
		@RequestMapping(value = "/employee/add", method = RequestMethod.GET)
	    public String add(Model model) {
	        model.addAttribute("add", new Employee());

	        return "employee/add";
	    }
		
		@RequestMapping(value = "/employee/add", method = RequestMethod.POST)
	    public String add(@RequestParam(value = "inputName")String name, @RequestParam(value = "inputEmail")String email, @RequestParam(value = "inputPwd")String pwd){
			Employee user = new Employee();
			user.setName(name);
			user.setEmail(email);
			user.setPwd(pwd);

	        employeeFacade.save(user);

	        return "redirect:/employee/" + user.getId();
	    }
		
		// show user
		@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
		public String showUser(@PathVariable("id") long id, Model model) {

			logger.debug("showUser() id: {}", id);

			Employee emp = employeeFacade.findById(id);
			if (emp == null) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "User not found");
			}
			model.addAttribute("user", emp);

			return "employee/show";

		}
		
		// delete user
		@RequestMapping(value = "/employee/{id}/delete", method = RequestMethod.POST)
		public String deleteUser(@PathVariable("id") long id,
			final RedirectAttributes redirectAttributes) {

			logger.debug("deleteUser() : {}", id);

			employeeFacade.delete(id);

			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "User is deleted!");

			return "redirect:/employee";

		}
		
		@RequestMapping(value = "/employee/{id}/update", method = RequestMethod.GET)
		public String getEmployeeForm(Model model,@PathVariable("id") long id) {
			Employee user = employeeFacade.findById(id);
			model.addAttribute("name", user.getName());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("pwd", user.getPwd());
			return "employee/update";
		}

		@RequestMapping(value = "/employee/{id}/update", method = RequestMethod.POST)
		public String updateEmployee(@RequestParam(value = "inputName") String name,
				@RequestParam(value = "inputEmail") String email, 
				@RequestParam(value = "inputPwd") String pwd, 
				Model model, @PathVariable("id") long id) {
			Employee user = employeeFacade.findById(id);
			user.setName(name);
			user.setEmail(email);
			user.setPwd(pwd);
			employeeFacade.save(user);
			model.addAttribute("add", new Employee());
			return "redirect:/employee";
			
		}
}
