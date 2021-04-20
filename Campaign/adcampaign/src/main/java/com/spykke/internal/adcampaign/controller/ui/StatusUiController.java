package com.spykke.internal.adcampaign.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spykke.internal.adcampaign.status.StatusServiceImpl;
import com.spykke.internal.adcampaigndb.entity.Status;


@Controller
public class StatusUiController {
	
	@Autowired
	StatusServiceImpl statusService;

	@GetMapping(value = "/status") 
    public String sample(Model model) { 
		List<Status> statuses = statusService.findAll();
		model.addAttribute("statuses", statuses);
    	return "status";
    }
	
	@PostMapping("/status/addNew")
	public String addNew(Status status) {
		status.setIsDeleted(false);
		statusService.save(status);
		return "redirect:/status";
	}
	
	@RequestMapping(value="/status/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Status status) {
		status.setIsDeleted(false);
		statusService.save(status);
		return "redirect:/status";
	}
	
	@RequestMapping(value="/status/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		statusService.deleteById(id);
		return "redirect:/status";
	}
	
}
