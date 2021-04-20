package com.spykke.internal.adcampaign.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spykke.internal.AppUser.AppUserServiceImpl;
import com.spykke.internal.adcampaign.dao.CouponUser;
import com.spykke.internal.adcampaign.repository.AppUserRepository;
import com.spykke.internal.commondb.entity.AppUser;

@Controller
public class AppUserUiController {
	
	@Autowired
	AppUserServiceImpl appUserService;
	
	@Autowired
	AppUserRepository appUserRepository; 

	@GetMapping(value = "/appuser") 
    public String sample(Model model) { 
		return findAllByPage(model, 1, "nickname","asc");
    }
	
	@GetMapping(value = "/appuser/page/{pageNumber}") 
    public String findAllByPage(Model model, 
    		@PathVariable("pageNumber") int currentPage,
    		@Param("sortField") String sortField,
    		@Param("sortDir") String sortDir) { 
		Page<AppUser> page = appUserService.findAllPageable(currentPage, sortField, sortDir);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		
		List<AppUser> appUsers = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("appUsers", appUsers);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		
		String reverseSortDir = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
    	return "appuser";
    }
	
	@GetMapping(value = "/couponcodeusers") 
    public String couponcodeUsers(Model model) { 
		List<CouponUser> couponUsers = appUserRepository.getCouponUsers();
		model.addAttribute("couponUsers", couponUsers);
    	return "couponcodeusers";
    }
	
	

}
