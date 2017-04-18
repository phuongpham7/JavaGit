package edu.mumscrum.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mumscrum.domain.LogHour;
import edu.mumscrum.domain.Sprint;
import edu.mumscrum.domain.UserStory;
import edu.mumscrum.service.ProductService;
import edu.mumscrum.service.SprintService;
import edu.mumscrum.service.UserStoryService;

@Controller
@RequestMapping("/sprint")
public class SprintController {
	@Autowired
	private SprintService sprintService;
	@Autowired
	private UserStoryService usService;
	@Autowired
	private ProductService productService;
	
	public SprintController() {
		// TODO Auto-generated constructor stub
	}
	@RequestMapping(path="/",method= RequestMethod.GET)
	public String getlist(Model model){
		model.addAttribute("sprints", sprintService.findAll());
		return "sprint/list";
	}

	@RequestMapping(path="/add",method= RequestMethod.GET)
	public String addSprint(Model model){
		model.addAttribute("product", productService.getAllProducts().get(0));
		model.addAttribute("userStories", usService.findUserStoriesNotInSprint());
		return "sprint/add";
	}
	
	@RequestMapping(path="/add",method= RequestMethod.POST)
	public String addSprint(@ModelAttribute Sprint sprint, BindingResult result, ModelMap model, @RequestParam("name") String name, @RequestParam("targetDate") String targetDate, @RequestParam("startDate") String startDate, @RequestParam("userStoryList") List<UserStory> userStories) throws ParseException{
		if (userStories == null)
		{
			model.addAttribute("product", productService.getAllProducts().get(0));
			model.addAttribute("userStories", usService.findUserStoriesNotInSprint());
			result.reject("error", "Sprint should have at least one user story.");

			return "sprint/add";
		}
		else
		{
			sprint.setName(name);
			sprint.setUserStoryList(userStories);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			sprint.setStartDate(df.parse(startDate));
			sprint.setDueDate(df.parse(targetDate));
			sprintService.save(sprint);
			model.addAttribute("sprint", sprint);
			return "redirect:/sprint/";
		}
	}
	
	
	@RequestMapping(path="/burndown",method= RequestMethod.GET)
	public String generateBurndown(Model model, @RequestParam(value = "sprintId", required = true) String sprintId) {
		
		Sprint sprint = sprintService.getSprint(Long.parseLong(sprintId));
		
		long startTime = sprint.getStartDate().getTime();
		long endTime = sprint.getDueDate().getTime();
		
		int numOfDays = (int)((endTime-startTime)/(1000 * 60 * 60 * 24));
		List<UserStory> usList = sprint.getUserStoryList();
		
		Map<Integer, Integer> effortMap = new HashMap<>();
		Map<Integer, List<Pair<Integer,Integer>>> hourMap = new HashMap<>();
		List<Integer> bd = new ArrayList<>();
		
		for (UserStory userStory : usList) {
			effortMap.put((int)userStory.getId(), userStory.getEstimatedHours());
			List<Pair<Integer, Integer>> hourList = new ArrayList<>();
			for (LogHour lh : userStory.getTesterLogHour()) {
				int day = (int)((lh.getTimeStamp().getTime()-startTime)/(1000 * 60 * 60 * 24));
				hourList.add(new Pair(day, lh.getHoursDone()));
			}
			hourMap.put((int)userStory.getId(), hourList);
		}
		
		int totalEffort = 0;
		int totalSprintEffort = 0;
		
		for (Integer integer : effortMap.values()) {
			totalEffort += integer;
			totalSprintEffort += integer;
		}
		
		int day = 0;
		boolean ending = false;
		while(!ending) {
			ending = true;
			bd.add(totalEffort);
			int effortToday = 0;
			for (List<Pair<Integer, Integer>> p : hourMap.values()) {
				if (p.size() > 0) {
					ending = false;
				}
				Iterator<Pair<Integer, Integer>> i = p.iterator();
				while(i.hasNext()) {
					Pair<Integer, Integer> pair = i.next();
					if(pair.first == day) {
						effortToday += pair.second;
						i.remove();
					}
				}
				
			}
			day++;
			totalEffort -= effortToday;
		}
		
		int actualDays = numOfDays;
		if (numOfDays < bd.size())
			actualDays = bd.size();
		
		int effortPerDay = totalSprintEffort/numOfDays;
		
		List<Integer> supposed = new ArrayList<>();
		List<Integer> days = new ArrayList<>();
		for (int ii = 0; ii <= actualDays; ii++) {
			days.add(ii);
			supposed.add(totalSprintEffort-(ii*effortPerDay));
		}
						
		model.addAttribute("totalSprintEffort", totalEffort);
		model.addAttribute("supposed", supposed);
		model.addAttribute("burndown", bd);
		model.addAttribute("days", days);
		
		return "sprint/burndown";

	}
	
	class Pair<F, S> {
	    private F first; //first member of pair
	    private S second; //second member of pair

	    public Pair(F first, S second) {
	        this.first = first;
	        this.second = second;
	    }

	    public void setFirst(F first) {
	        this.first = first;
	    }

	    public void setSecond(S second) {
	        this.second = second;
	    }

	    public F getFirst() {
	        return first;
	    }

	    public S getSecond() {
	        return second;
	    }
	}

}
