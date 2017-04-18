package edu.mumscrum.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import antlr.StringUtils;
import edu.mumscrum.domain.Employee;
import edu.mumscrum.domain.UserStory;
import edu.mumscrum.hrSubSystem.EmployeeService;
import edu.mumscrum.service.UserStoryService;

@Controller
@RequestMapping("/development")
public class EffortDevelopmentEstimateController {
	
	@Autowired
	private UserStoryService usService;
	
	@Autowired
	private EmployeeService employeeService;
	
	int employee = 1;

	public EffortDevelopmentEstimateController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(path="/",method= RequestMethod.GET)
	public String getUserStoriesOfDeveloper(Model model, HttpSession session){
		Employee e = (Employee) session.getAttribute("loggedInUser");
		if (e == null)
			e = (Employee) employeeService.findAllDevelopers().get(employee);
		model.addAttribute("userStories", usService.findByDeveloper(e));
		return "development/list";
	}
	
	@RequestMapping(path="/{id}",method= RequestMethod.GET)
	public String view(Model model, @PathVariable("id") long id){
		model.addAttribute("estimatedHours", usService.getUserStory(id).getEstimatedHours());
		model.addAttribute("userStory", usService.getUserStory(id));
		return "development/estimateDevelopmentEffort";
	}
	
	@RequestMapping(path="/save",method= RequestMethod.POST)
	public String save(@ModelAttribute UserStory userStory, HttpSession session, BindingResult result, ModelMap model, @RequestParam("estimatedHours") int estimatedHours){
//		if (!estimatedHours.matches("[-+]?\\d*\\.?\\d+")){
//			model.addAttribute("estimatedHours", estimatedHours);
//			model.addAttribute("userStory", userStory);
//			result.reject("error", "Estimated Hours should be numeric");
//
//			return "development/estimateDevelopmentEffort";
//		}
//		else{
//			//TODO: SAVE CHANGES
//			userStory.setEstimatedHours(Integer.parseInt(estimatedHours));
//			usService.save(userStory);
//			Employee e = employeeService.findAllDevelopers().get(employee);
//			model.addAttribute("userStories", usService.findByDeveloper(e));
//			return "development/list";
//		}
		UserStory updatedUS = usService.getUserStory(userStory.getId());
		updatedUS.setEstimatedHours(estimatedHours);
		usService.save(updatedUS);
		Employee e = (Employee) session.getAttribute("loggedInUser");
		if (e == null)
			e = (Employee) employeeService.findAllDevelopers().get(employee);
		model.addAttribute("userStories", usService.findByDeveloper(e));
		return "development/list";
	}
	
}
