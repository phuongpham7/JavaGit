package edu.mumscrum.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mumscrum.domain.ReleaseBacklog;
import edu.mumscrum.domain.UserStory;
import edu.mumscrum.service.ProductService;
import edu.mumscrum.service.ReleaseService;
import edu.mumscrum.service.UserStoryService;

@Controller
@RequestMapping("/release")
public class ReleaseController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserStoryService usService;
	
	@Autowired
	private ReleaseService releaseService;
	
	public ReleaseController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(path="/",method= RequestMethod.GET)
	public String getlist(Model model){
		model.addAttribute("releases", releaseService.findAll());
		return "release/list";
	}
	
	@RequestMapping(path="/add",method= RequestMethod.GET)
	public String addRelease(Model model){
		model.addAttribute("product", productService.getAllProducts().get(0));
		model.addAttribute("userStories", usService.findUserStoriesNotInReleases());
		return "release/add";
	}
	
	@RequestMapping(path="/add",method= RequestMethod.POST)
	public String addRelease(@ModelAttribute ReleaseBacklog release, BindingResult result, ModelMap model, @RequestParam("name") String name, @RequestParam("targetDate") String targetDate, @RequestParam("userStoryList") List<UserStory> userStories){
		if (userStories == null)
		{
			model.addAttribute("product", productService.getAllProducts().get(0));
			model.addAttribute("userStories", usService.findUserStoriesNotInReleases());
			result.reject("error", "Release should have at least one user story.");

			return "release/add";
		}
		else
		{
			release.setName(name);
			release.setTargetDate(new Date(targetDate));
			release.setUserStories(userStories);
			release.setProduct(productService.getAllProducts().get(0));
			releaseService.save(release);
			model.addAttribute("release", release);
			return "redirect:/release/";
		}
	}
}
