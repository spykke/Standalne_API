package com.spykke.internal.adcampaign.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spykke.internal.adcampaign.entity.TrackingBannerAd;
import com.spykke.internal.adcampaign.repository.TrackingBannerRepository;

@Controller
public class AdTrackingController {
	
	@Autowired
	TrackingBannerRepository trackingBannerRepository;
	
	@GetMapping(value = "/banneradtracking") 
    public String banneradtracking(Model model) { 
		List<TrackingBannerAd> trackingList = trackingBannerRepository.findAll();
		model.addAttribute("trackingList", trackingList);
    	return "banneradtracking";
    }

}
