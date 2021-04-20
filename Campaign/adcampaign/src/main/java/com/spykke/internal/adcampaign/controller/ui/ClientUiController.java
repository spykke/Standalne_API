package com.spykke.internal.adcampaign.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spykke.internal.adcampaign.client.ClientServiceImpl;
import com.spykke.internal.adcampaigndb.entity.Client;


@Controller
public class ClientUiController {
	
	@Autowired
	ClientServiceImpl clientService;

	@GetMapping(value = "/client") 
    public String sample(Model model) { 
		List<Client> clients = clientService.findAll();
		model.addAttribute("clients", clients);
    	return "client";
    }
	
	@PostMapping("/clients/addNew")
	public String addNew(Client client) {
		client.setIsDeleted(false);
		clientService.save(client);
		return "redirect:/client";
	}
	
	@RequestMapping(value="/clients/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Client client) {
		client.setIsDeleted(false);
		clientService.save(client);
		return "redirect:/client";
	}
	
	@RequestMapping(value="/clients/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		clientService.deleteById(id);
		return "redirect:/client";
	}
	
}
