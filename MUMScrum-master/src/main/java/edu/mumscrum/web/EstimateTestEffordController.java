package edu.mumscrum.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mumscrum.domain.Employee;
import edu.mumscrum.domain.UserStory;
import edu.mumscrum.service.UserStoryService;

@Controller
public class EstimateTestEffordController {
	@Autowired
	private UserStoryService userStoryService;
	
	@RequestMapping(value = "/userStory/estimateTestEfford", method = RequestMethod.GET)
    public String add(Model model, HttpSession session) {
		Employee user = (Employee) session.getAttribute("loggedInUser");
		model.addAttribute("allUserStory", userStoryService.findByTesterId(user.getId()));
        return "userStory/estimateTestEfford";
    }
	
	@RequestMapping(value = "/userStory/estimateTestEfford", method = RequestMethod.POST)
    public String add(@RequestParam(value = "userStoryId")String userStoryId, @RequestParam(value = "estimateHours")String estimateHours){
		UserStory inputUserStory = userStoryService.getUserStory(Integer.parseInt(userStoryId));
		inputUserStory.setEstimatedHours(Integer.parseInt(estimateHours));

        userStoryService.save(inputUserStory);

        return "redirect:/employee/tester";
    }
}
