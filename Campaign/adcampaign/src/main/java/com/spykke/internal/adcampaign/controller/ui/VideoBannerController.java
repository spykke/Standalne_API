package com.spykke.internal.adcampaign.controller.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spykke.internal.adcampaign.client.ClientServiceImpl;
import com.spykke.internal.adcampaign.entity.BannerAd;
import com.spykke.internal.adcampaign.entity.BannerStation;
import com.spykke.internal.adcampaign.repository.BannerStationRepository;
import com.spykke.internal.adcampaign.repository.VideoBannerRepository;
import com.spykke.internal.adcampaigndb.entity.Campaign;
import com.spykke.internal.adcampaigndb.entity.CampaignPowerBank;
import com.spykke.internal.adcampaigndb.entity.Client;
import com.spykke.internal.commondb.entity.City;
import com.spykke.internal.commondb.entity.Merchant;
import com.spykke.internal.commondb.entity.PowerBank;

@Controller
public class VideoBannerController {
	
	@Autowired
	VideoBannerRepository videoBannerService;
	
	@Autowired
	ClientServiceImpl clientService;
	
	@Autowired
	BannerStationRepository bannerStationRepository;
	
	@GetMapping(value = "/banner") 
    public String sample(Model model) { 
		List<Client> clients = clientService.findByType("VIDEOBANNER");
		List<BannerAd> banners = videoBannerService.findAll();
		model.addAttribute("clients", clients);
		model.addAttribute("banners", banners);
    	return "banner";
    }
	
	@PostMapping("/banner/addNew")
	public String addNew(BannerAd state) {
		state.setIsDeleted(false);
		videoBannerService.save(state);
		return "redirect:/banner";
	}
	
	@RequestMapping(value="/banner/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(BannerAd state) {
		state.setIsDeleted(false);
		videoBannerService.save(state);
		return "redirect:/banner";
	}
	
	@RequestMapping(value="/banner/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Long id) {
		videoBannerService.deleteById(id);
		return "redirect:/banner";
	}
	
	@RequestMapping("banner/findById")
	@ResponseBody
	public BannerAd findById(long id) {
		BannerAd banner = videoBannerService.findById(id).get();
 	  return banner;
	}	
	
	@GetMapping("banner/station/{id}")
	public String showpowerbanks(@PathVariable("id") long id, Model model) {
		BannerAd banner = videoBannerService.findById(id).get();
		model.addAttribute("banner", banner);
		List<BannerStation> stationList = bannerStationRepository.findByBannerId(id);
		model.addAttribute("stationList", stationList);

		return "bannerstation";
	}
	
	@PostMapping("/banner/station")
	public String addBannerStation(BannerStation bannerstation) {
		return "redirect:/banner/station/"+bannerstation.getBanner().getId();
	}
	
	@RequestMapping(value="/bannerstation/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String deleteStation(Long id) {
		Long bannerId = bannerStationRepository.findById(id).get().getBanner().getId();
		bannerStationRepository.deleteById(id);
		return "redirect:/banner/station/"+bannerId;
	}
	
	@GetMapping("/banner/uploadstations/{id}")
	public String uploadStationtoBanner(@PathVariable("id") long id, Model model) {
		model.addAttribute("bannerid", id);
		return "uploadstations";
	}
	
	@PostMapping("/banner/uploadstationexcel")
    public String excelPowerbanktoCampaign(@RequestParam("id") long id,@RequestParam("uploadfile") MultipartFile file, Model model) {
		model.addAttribute("campaignid", id);
		try {

    		Workbook workbook = new XSSFWorkbook(file.getInputStream());
     
    		Sheet sheet = workbook.getSheetAt(0);
    		Iterator<Row> rows = sheet.iterator();
    		
    		List<BannerStation> bannerStationList = new ArrayList<>();
    		
    		int rowNumber = 0;
    		while (rows.hasNext()) {
    			Row currentRow = rows.next();
    			
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			}
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();

    			BannerStation bannerStation = new BannerStation();
    			BannerAd banner = new BannerAd();
    			banner.setId(id);
    			bannerStation.setBanner(banner);
    			
    			
    			int cellIndex = 0;
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = cellsInRow.next();
    				
    				if(cellIndex==0) { // ID
    					bannerStation.setStationId(currentCell.getStringCellValue());
    					
    				} 
    				cellIndex++;
    			}
    			
    			if(null!=bannerStation.getStationId() && !bannerStation.getStationId().isEmpty()) {
    				bannerStationList.add(bannerStation);
    			}
    		}
    		
    		// Close WorkBook
    		workbook.close();
			
    		bannerStationRepository.saveAll(bannerStationList);
    		
	      model.addAttribute("message", "File uploaded successfully!");
	    } catch (Exception e) {
	      model.addAttribute("message", "Fail! -> uploaded filename: " + file.getOriginalFilename());
	    }
	    
		return "uploadpowerbanks";
    }
	
	
}
