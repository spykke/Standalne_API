package com.spykke.internal.adcampaign.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spykke.internal.commondb.entity.Merchant;
import com.spykke.internal.merchant.MerchantServiceImpl;

@Controller
public class MerchantUiController {
	
	@Autowired
	MerchantServiceImpl merchantService;

	@GetMapping(value = "/merchant") 
    public String sample(Model model) { 
		return findAllByPage(model, 1, "name","asc");
    }
	
	@GetMapping(value = "/merchant/page/{pageNumber}") 
    public String findAllByPage(Model model, 
    		@PathVariable("pageNumber") int currentPage,
    		@Param("sortField") String sortField,
    		@Param("sortDir") String sortDir) { 
		Page<Merchant> page = merchantService.findAllPageable(currentPage, sortField, sortDir);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		
		List<Merchant> merchants = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("merchants", merchants);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		
		String reverseSortDir = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
    	return "merchant";
    }

}
