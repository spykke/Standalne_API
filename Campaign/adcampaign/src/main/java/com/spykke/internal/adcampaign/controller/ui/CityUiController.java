package com.spykke.internal.adcampaign.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spykke.internal.commondb.entity.City;
import com.spykke.internal.commondb.entity.State;
import com.spykke.internal.state.StateServiceImpl;
import com.spykke.internal.city.CityServiceImpl;

@Controller
public class CityUiController {
	
	@Autowired
	CityServiceImpl cityService;
	
	@Autowired
	StateServiceImpl stateService;

	@GetMapping(value = "/city") 
    public String sample(Model model) { 
		List<City> citys = cityService.findAll();
		List<State> states = stateService.findAll();
		model.addAttribute("citys", citys);
		model.addAttribute("states", states);
    	return "city";
    }
	
	@PostMapping("/citys/addNew")
	public String addNew(City city) {
		System.out.println("City "+city.getName() + " "+ city.getName()+ " " +city.getState().getId());
		city.setIsDeleted(false);
		cityService.save(city);
		return "redirect:/city";
	}
	
	@RequestMapping(value="/citys/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(City city) {
		city.setIsDeleted(false);
		cityService.save(city);
		return "redirect:/city";
	}
	
	@RequestMapping(value="/citys/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		cityService.deleteById(id);
		return "redirect:/city";
	}
	
}
