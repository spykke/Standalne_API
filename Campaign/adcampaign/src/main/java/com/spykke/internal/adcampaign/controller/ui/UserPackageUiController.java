package com.spykke.internal.adcampaign.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spykke.internal.commondb.entity.UserPackage;
import com.spykke.internal.userpackage.UserPackageServiceImpl;

@Controller
public class UserPackageUiController {
	
	@Autowired
	UserPackageServiceImpl userPackageService;

	@GetMapping(value = "/userPackage") 
    public String sample(Model model) { 
		return findAllByPage(model, 1, "title","asc");
    }
	
	@GetMapping(value = "/userPackage/page/{pageNumber}") 
    public String findAllByPage(Model model, 
    		@PathVariable("pageNumber") int currentPage,
    		@Param("sortField") String sortField,
    		@Param("sortDir") String sortDir) { 
		Page<UserPackage> page = userPackageService.findAllPageable(currentPage, sortField, sortDir);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		
		List<UserPackage> userPackages = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("userPackages", userPackages);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		
		String reverseSortDir = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
    	return "userPackage";
    }
	
}
