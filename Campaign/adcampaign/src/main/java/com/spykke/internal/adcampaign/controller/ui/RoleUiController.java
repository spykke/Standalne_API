package com.spykke.internal.adcampaign.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spykke.internal.adcampaign.entity.ReportData;
import com.spykke.internal.adcampaign.repository.ReportDataJpaRepository;

@Controller
public class RoleUiController {
	
	@Autowired
	ReportDataJpaRepository reportDataJpaRepository;

	@GetMapping("dailycabinetstatus")
	public String showUpdateForm(Model model) {
		List<ReportData> dataList = reportDataJpaRepository.findAll();
		model.addAttribute("dataList", dataList);
		return "reportdata";
	}

}
