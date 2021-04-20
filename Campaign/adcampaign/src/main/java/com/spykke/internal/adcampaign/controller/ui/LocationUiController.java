package com.spykke.internal.adcampaign.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spykke.internal.adcampaign.location.LocationServiceImpl;
import com.spykke.internal.adcampaigndb.entity.Location;
import com.spykke.internal.city.CityServiceImpl;
import com.spykke.internal.commondb.entity.City;
import com.spykke.internal.commondb.entity.State;
import com.spykke.internal.state.StateServiceImpl;

@Controller
public class LocationUiController {
	
	@Autowired
	LocationServiceImpl locationService;
	
	@Autowired
	StateServiceImpl stateService;
	
	@Autowired
	CityServiceImpl cityService;

	@GetMapping(value = "/location") 
    public String sample(Model model) { 
		List<Location> locations = locationService.findAll();
		List<State> states = stateService.findAll();
		List<City> citys = cityService.findAll();
		model.addAttribute("locations", locations);
		model.addAttribute("states", states);
		model.addAttribute("citys", citys);
    	return "location";
    }
	
	@PostMapping("/locations/addNew")
	public String addNew(Location location) {
		System.out.println("Location "+location.getState().getName() + " "+ location.getCity().getName());
		
		locationService.save(location);
		return "redirect:/location";
	}
	
	@RequestMapping(value="/locations/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Location location) {
		locationService.save(location);
		return "redirect:/location";
	}
	
	@RequestMapping(value="/locations/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		locationService.deleteById(id);
		return "redirect:/location";
	}
	
}
