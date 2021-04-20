package com.spykke.internal.adcampaign.controller.ui;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spykke.internal.adcampaigndb.entity.Campaign;
import com.spykke.internal.adcampaigndb.repository.CampaignJpaRepository;

@Controller
public class DashboardController {
	
	@Autowired
	CampaignJpaRepository campaignRepository;
	
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) { 
    	
    	Date today = new Date();
    	
    	List<Campaign> upcomingCampaignList = campaignRepository.findAllByStartDateAfter(today);
    	List<Campaign> activeCampaignList = campaignRepository.findAllByStartDateBeforeAndEndDateAfter(today, today);
		model.addAttribute("activecampaigns", activeCampaignList);
		model.addAttribute("upcomingcampaigns", upcomingCampaignList);
		return "index";
    }
    
    @GetMapping(value = "/sample") 
    public String sample(Model model) { 
    	return "sample";
    }
    
    @GetMapping(value = "/uc") 
    public String uc(Model model) { 
    	return "underconstruction";
    }
	
}
