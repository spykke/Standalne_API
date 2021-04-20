package com.spykke.internal.adcampaign.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spykke.internal.commondb.entity.SysUser;
import com.spykke.internal.sysuser.SysUserServiceImpl;

@Controller
public class SysUserUiController {
	
	@Autowired
	SysUserServiceImpl sysUserService;

	@GetMapping(value = "/sysUser") 
    public String sample(Model model) { 
		return findAllByPage(model, 1, "login_name","asc");
    }
	
	@GetMapping(value = "/sysUser/page/{pageNumber}") 
    public String findAllByPage(Model model, 
    		@PathVariable("pageNumber") int currentPage,
    		@Param("sortField") String sortField,
    		@Param("sortDir") String sortDir) { 
		Page<SysUser> page = sysUserService.findAllPageable(currentPage, sortField, sortDir);
		long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		
		List<SysUser> sysUsers = page.getContent();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("sysUsers", sysUsers);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		
		String reverseSortDir = sortDir.equalsIgnoreCase("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
    	return "sysUser";
    }
}
