package edu.mumscrum.web;

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

import edu.mumscrum.domain.Employee;
import edu.mumscrum.domain.Product;
import edu.mumscrum.domain.Role;
import edu.mumscrum.domain.UserStory;

import edu.mumscrum.hrSubSystem.IHRSubsystem;

import edu.mumscrum.hrSubSystem.EmployeeService;

import edu.mumscrum.service.ProductService;
import edu.mumscrum.service.UserStoryService;

@Controller
@RequestMapping("/userstory")
public class UserStoryController {
	@Autowired
	private UserStoryService usService;
	@Autowired
	private ProductService productService;

	@Autowired
	private IHRSubsystem hr;

	
	@Autowired
	private EmployeeService employeeService;

	public UserStoryController() {
		// TODO Auto-generated constructor stub
	}
	@RequestMapping(path="/",method= RequestMethod.GET)
	public String getlist(Model model){
//		UserStory us=new UserStory();
//		us.setTitle("title");
//		usService.add(us);
		model.addAttribute("userStories", usService.findAll());
		return "userStory/list";
	}
	@RequestMapping(path="/add",method= RequestMethod.GET)
	public String addUserStory(Model model){
		model.addAttribute("developers",hr.findDeveloperAndTester());
		return "userStory/add";
	}
	

	@RequestMapping(path="/edit/{id}",method= RequestMethod.GET)
	public String editUserStory(Model model,@PathVariable long id){
		model.addAttribute("developers",hr.findDeveloperAndTester());
		model.addAttribute("userStory",usService.getUserStory(id));
		return "userStory/edit";
	}
	@RequestMapping(path="/delete/{id}",method= RequestMethod.GET)
	public String deleteUserStory(Model model,@PathVariable long id){
		try{
		usService.delete(id);}
		catch(Exception e){
			
		}
		return "redirect:/userstory/";
	}

	@RequestMapping(path="/add",method= RequestMethod.POST)
	public String addUserStory(UserStory us){
//		Employee developer=hr.findById(id);
		Product p=productService.getAllProducts().get(0);
		us.setProduct(p);
//		us.se.setAssignedTo(developer);
		//us.setEstimatedHours(0);
		us=usService.save(us);
		p.getUserStoryList().add(us);
		
		
		if(productService.save(p)!=null){
			return "redirect:/userstory/";
		}else{
			return "userStory/add";
		}
		
	}
	
	@RequestMapping(path="/{id}",method= RequestMethod.GET)
	public String view(Model model, @PathVariable("id") long id){
		model.addAttribute("allDevelopers", employeeService.findAllDevelopers());
		model.addAttribute("allTesters", employeeService.findAllTesters());
		model.addAttribute("userStory", usService.getUserStory(id));
		return "userStory/assignUS";
	}
	
	@RequestMapping(path="/save",method= RequestMethod.POST)
	public String save(@ModelAttribute UserStory userStory, BindingResult result, ModelMap model, @RequestParam("developer") Long developerId,  @RequestParam("tester") Long testerId){
		if (!usService.IsAbleToAssign(userStory.getId())){
			model.addAttribute("allDevelopers", employeeService.findAllDevelopers());
			model.addAttribute("allTesters", employeeService.findAllTesters());
			model.addAttribute("userStory", userStory);
			result.reject("error", "Cannot assign because estimate hour is not 0");

			return "userStory/assignUS";
		}
		else{
			//TODO: SAVE CHANGES
			UserStory updatedUS = usService.getUserStory(userStory.getId());
			Employee dev = employeeService.findById(developerId);
			updatedUS.setDeveloper(dev);
			Employee tester = employeeService.findById(testerId);
			updatedUS.setTester(tester);
			usService.save(updatedUS);
			model.addAttribute("userStories", usService.findAll());
			return "userStory/list";
		}

	}

}
