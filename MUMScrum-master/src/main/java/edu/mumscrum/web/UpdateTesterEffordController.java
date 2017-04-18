package edu.mumscrum.web;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mumscrum.domain.Employee;
import edu.mumscrum.domain.LogHour;
import edu.mumscrum.domain.UserStory;
import edu.mumscrum.service.UserStoryService;

@Controller
public class UpdateTesterEffordController {
	@Autowired
	private UserStoryService userStoryService;
	
	
	@RequestMapping(value = "/employee/tester", method= RequestMethod.GET)
	public String getUserStoryList(Model model, HttpSession session) {
		Employee user = (Employee) session.getAttribute("loggedInUser");
		List<UserStory> us = userStoryService.findByTesterId(user.getId());
		//List<LogHour> loghours= us.stream().map(x->x.getTesterLogHour()).collect(Collectors.toList());
		//model.addAttribute("currentTime");
		model.addAttribute("userStories", userStoryService.findByTesterId(user.getId()));
		return "employee/tester";
	}
	
	@RequestMapping(value = "/userStory/updateTestEfford", method= RequestMethod.GET)
	public String getUpdateTestEffordForm(Model model, HttpSession session) {
		Employee user = (Employee) session.getAttribute("loggedInUser");
		model.addAttribute("allUserStory", userStoryService.findByTesterId(user.getId()));
		model.addAttribute("currentDate", LocalDate.now());
		return "userStory/updateTestEfford";
	}

	@RequestMapping(value="/userStory/updateTestEfford", method= RequestMethod.POST)
	public String updateTestEfford(@RequestParam(value="timeStamp", required = true) String timeStamp, 
			@RequestParam(value= "hoursDone", required = true) String hoursDone,
			@RequestParam(value= "userStoryId", required = true) String userStoryId){
			UserStory inputUserStory = userStoryService.getUserStory(Integer.parseInt(userStoryId));
			LocalDate inputTimeStamp = LocalDate.parse(timeStamp);
			LogHour inputLogHour = new LogHour(inputTimeStamp, Integer.parseInt(hoursLeft));
			LogHour inputLogHour = new LogHour(d, Integer.parseInt(hoursDone));
			List<LogHour> logHourList = inputUserStory.getTesterLogHour();
			for(int i = 0; i< logHourList.size(); i++){
				if(logHourList.get(i).getTimeStamp().equals(inputLogHour.getTimeStamp())){
					logHourList.remove(i);
					
				}
			}				
			logHourList.add(inputLogHour);
			inputUserStory.setTesterLogHour(logHourList);
			userStoryService.save(inputUserStory);			

		return "redirect:/employee/tester";
	}
}
