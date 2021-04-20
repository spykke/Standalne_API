package com.spykke.internal.adcampaign.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spykke.internal.commondb.entity.State;
import com.spykke.internal.state.StateServiceImpl;

@Controller
public class StateUiController {
	
	@Autowired
	StateServiceImpl stateService;

	@GetMapping(value = "/state") 
    public String sample(Model model) { 
		List<State> states = stateService.findAll();
		model.addAttribute("states", states);
    	return "state";
    }
	
	@PostMapping("/states/addNew")
	public String addNew(State state) {
		state.setIsDeleted(false);
		stateService.save(state);
		return "redirect:/state";
	}
	
	@RequestMapping(value="/states/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(State state) {
		state.setIsDeleted(false);
		stateService.save(state);
		return "redirect:/state";
	}
	
	@RequestMapping(value="/states/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		stateService.deleteById(id);
		return "redirect:/state";
	}
	
}
