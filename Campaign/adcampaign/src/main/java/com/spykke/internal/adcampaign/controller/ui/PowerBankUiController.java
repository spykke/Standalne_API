package com.spykke.internal.adcampaign.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spykke.internal.commondb.entity.PowerBank;
import com.spykke.internal.powerbank.PowerBankServiceImpl;

@Controller
public class PowerBankUiController {
	
	@Autowired
	PowerBankServiceImpl powerBankService;

	@GetMapping(value = "/powerbank") 
    public String sample(Model model) { 
		return findAllByPage(model, 1, "powerBankNo","asc");
    }
	
	@GetMapping(value = "/powerBank/page/{pageNumber}") 
    public String findAllByPage(Model model, 
    		@PathVariable("pageNumber") int currentPage,
    		@Param("sortField") String sortField,
    		@Param("sortDir") String sortDir) { 
		Page<PowerBank> page = powerBankService.findAllPageable(currentPage, sortField, sortDir);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		
		List<PowerBank> powerBanks = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("powerBanks", powerBanks);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		
		String reverseSortDir = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
    	return "powerbank";
    }
	
}
