package com.spykke.internal.adcampaign.controller.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spykke.internal.adcampaign.repository.CaibnetJpaRepository;
import com.spykke.internal.adcampaign.repository.CaibnetPositionJpaRepository;
import com.spykke.internal.adcampaign.repository.OrderRepository;
import com.spykke.internal.adcampaign.repository.PowerBankRepository;
import com.spykke.internal.adcampaigndb.entity.Campaign;
import com.spykke.internal.adcampaigndb.entity.CampaignLocation;
import com.spykke.internal.adcampaigndb.entity.CampaignPowerBank;
import com.spykke.internal.adcampaigndb.entity.Location;
import com.spykke.internal.adcampaigndb.repository.CampaignJpaRepository;
import com.spykke.internal.adcampaigndb.repository.CampaignLocationJpaRepository;
import com.spykke.internal.adcampaigndb.repository.CampaignPowerBankJpaRepository;
import com.spykke.internal.adcampaigndb.repository.ClientJpaRepository;
import com.spykke.internal.adcampaigndb.repository.LocationJpaRepository;
import com.spykke.internal.adcampaigndb.repository.StatusJpaRepository;
import com.spykke.internal.commondb.entity.Cabinet;
import com.spykke.internal.commondb.entity.CabinetPosition;
import com.spykke.internal.commondb.entity.Merchant;
import com.spykke.internal.commondb.entity.Order;
import com.spykke.internal.commondb.entity.PowerBank;
import com.spykke.internal.commondb.entity.Shop;
import com.spykke.internal.merchant.MerchantJpaRepository;
import com.spykke.internal.shop.ShopJpaRepository;

@Controller
@RequestMapping("/ui/campaign/")
public class CampaignUiController {

	@Autowired
	CampaignJpaRepository campaignRepository;

	@Autowired
	ClientJpaRepository clientJpaRepository;

	@Autowired
	StatusJpaRepository statusJpaRepository;

	@Autowired
	LocationJpaRepository locationJpaRepository;

	@Autowired
	PowerBankRepository powerBankJpaRepository;

	@Autowired
	CampaignLocationJpaRepository campaignLocationJpaRepository;

	@Autowired
	CampaignPowerBankJpaRepository campaignPowerBankJpaRepository;

	@Autowired
	MerchantJpaRepository merchantJpaRepository;

	@Autowired
	ShopJpaRepository shopJpaRepository;

	@Autowired
	CaibnetJpaRepository caibnetJpaRepository;

	@Autowired
	CaibnetPositionJpaRepository caibnetPositionJpaRepository;

	@Autowired
	OrderRepository orderJpaRepository;

	@GetMapping("form")
	public String showSignUpForm(Campaign campaign, Model model) {
		model.addAttribute("clients", clientJpaRepository.findAll());
		model.addAttribute("statuses", statusJpaRepository.findAllByIsDeletedFalse());
		model.addAttribute("locations", locationJpaRepository.findAll());
		model.addAttribute("powerbanks", powerBankJpaRepository.findTop10ByOrderByReturnTimeDesc());

		return "add-campaign";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model) {
		List<Campaign> campaignList = campaignRepository.findAll();
		model.addAttribute("campaigns", campaignList);
		return "list-campaign";
	}

	@PostMapping("add")
	public String addCampaign(@Valid Campaign campaign, BindingResult result, Model model) {

		campaign.setIsDeleted(false);
		Campaign createdCampaign = campaignRepository.save(campaign);

		for (Location location : createdCampaign.getLocations()) {
			CampaignLocation campaignLocation = new CampaignLocation();
			campaignLocation.setCampaign(createdCampaign);
			campaignLocation.setLocation(location);
			campaignLocationJpaRepository.save(campaignLocation);
		}

		for (PowerBank powerBank : createdCampaign.getPowerBanks()) {
			CampaignPowerBank campaignPowerBank = new CampaignPowerBank();
			campaignPowerBank.setCampaign(createdCampaign);
			campaignPowerBank.setPowerBank(powerBank);
			campaignPowerBankJpaRepository.save(campaignPowerBank);
		}

		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Campaign campaign = campaignRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid campaign Id:" + id));
		model.addAttribute("campaign", campaign);
		model.addAttribute("clients", clientJpaRepository.findAll());
		model.addAttribute("statuses", statusJpaRepository.findAllByIsDeletedFalse());
		return "update-campaign";
	}
	
	@GetMapping("show/{id}")
	public String showCampaign(@PathVariable("id") long id, Model model) {
		Campaign campaign = campaignRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid campaign Id:" + id));
		model.addAttribute("campaign", campaign);
		
		Calendar endDate =  Calendar.getInstance();
		endDate.setTime(campaign.getEndDate());
		endDate.set(Calendar.HOUR_OF_DAY,23);
		endDate.set(Calendar.MINUTE,59);
		endDate.set(Calendar.SECOND,59);
		
		List<CampaignPowerBank> cpbList = campaignPowerBankJpaRepository.findByCampaignId(id);
		model.addAttribute("cpbList", cpbList);
		
		List<String> pbNos = new ArrayList<String>();
		
		for (CampaignPowerBank campaignPowerBank : cpbList) {
			pbNos.add(campaignPowerBank.getPowerBank().getPowerBankNo());
		}
		
		List<CampaignLocation> locationList = campaignLocationJpaRepository.findByCampaignId(id);
		model.addAttribute("locationList", locationList);
		
		List<Order> orderList = orderJpaRepository.findByPowerBankNoInAndCreateTimeBetween(pbNos, campaign.getStartDate(),
				endDate.getTime());
		
		model.addAttribute("orders", orderList);
		
		JSONObject  metrics= getUsageMetrics(orderList, pbNos, campaign.getStartDate(), endDate);
		List<List<String>> dataList = new ArrayList<>();
		JSONArray headerArray = (JSONArray) metrics.get("header");
		
		JSONArray dataArray = (JSONArray) metrics.get("data");
		
		for (Object currDataObj : dataArray) {
			JSONObject currJson = (JSONObject)currDataObj;
			List<String> currRowList = new ArrayList<String>();
			for (Object currHeaderObj : headerArray) {
				String currHeader = (String)currHeaderObj;
				currRowList.add(String.valueOf(currJson.get(currHeader)));
			}
			dataList.add(currRowList);
		}
		
		
		
		model.addAttribute("header", metrics.get("header"));
		model.addAttribute("data", dataList);
		
		return "show-campaign";
	}

	@PostMapping("update/{id}")
	public String updateCampaign(@PathVariable("id") long id, @Valid Campaign campaign, BindingResult result,
			Model model) {

		campaignRepository.save(campaign);
		model.addAttribute("campaigns", campaignRepository.findAll());
		return "list-campaign";
	}

	@GetMapping("delete/{id}")
	public String deleteCampaign(@PathVariable("id") long id, Model model) {
		Campaign campaign = campaignRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid campaign Id:" + id));
		campaign.setIsDeleted(true);
		campaignRepository.delete(campaign);
		model.addAttribute("campaigns", campaignRepository.findAll());
		return "list-campaign";
	}

	@GetMapping("pbfilter/{id}")
	public String showpowerbanks(@PathVariable("id") long id, Model model) {
		List<Merchant> merchants = merchantJpaRepository.findAllByOrderByNameAsc();
		Campaign campaign = campaignRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid campaign Id:" + id));
		model.addAttribute("campaign", campaign);
		model.addAttribute("merchants", merchants);

		List<CampaignPowerBank> cpbList = campaignPowerBankJpaRepository.findByCampaignId(id);
		model.addAttribute("cpbList", cpbList);
		return "powerbankfilter";
	}
	
	@GetMapping("campaignlocation/{id}")
	public String campaignlocation(@PathVariable("id") long id, Model model) {
		Campaign campaign = campaignRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid campaign Id:" + id));
		model.addAttribute("campaign", campaign);

		List<Location> locationList = locationJpaRepository.findAll();
		
		model.addAttribute("locations", locationList);
		
		List<CampaignLocation> cpbList = campaignLocationJpaRepository.findByCampaignId(id);
		model.addAttribute("cpbList", cpbList);
		return "campaign-location";
	}

	@GetMapping("addPowerBanktoCampaign")
	public String addPowerBanktoCampaign(Long campaignid,Long powerbankid, Model model) {
		
		Campaign campaign = campaignRepository.findById(campaignid)
				.orElseThrow(() -> new IllegalArgumentException("Invalid campaign Id:" + campaignid));
		
		CampaignPowerBank campaignPowerBank = new CampaignPowerBank();
		
		Optional<PowerBank> powerBank = powerBankJpaRepository.findById(powerbankid);
		
		campaignPowerBank.setCampaign(campaign);
		campaignPowerBank.setPowerBank(powerBank.get());
		campaignPowerBankJpaRepository.save(campaignPowerBank);
		List<Merchant> merchants = merchantJpaRepository.findAllByOrderByNameAsc();
		
		
		model.addAttribute("campaign", campaign);
		model.addAttribute("merchants", merchants);

		List<CampaignPowerBank> cpbList = campaignPowerBankJpaRepository.findByCampaignId(campaignid);
		model.addAttribute("cpbList", cpbList);
		return "powerbankfilter";
	}
	
	@RequestMapping("addPowerBankListtoCampaign")
	@ResponseBody
	public List<CampaignPowerBank> addPowerBankListtoCampaign(Long campaignid,String powerbankids) {
		System.out.println(campaignid);
		String[] powerbanks = powerbankids.replaceAll("\"", "").replaceAll("]", "").replace("[", "").split(",");
		
		List<CampaignPowerBank> campaignPowerBankList = new ArrayList<>();
		for (String powerbankId : powerbanks) {
			CampaignPowerBank campaignPowerBank = new CampaignPowerBank();
			Campaign campaign = new Campaign();
			PowerBank powerBank = powerBankJpaRepository.findById(Long.valueOf(powerbankId)).get();//new PowerBank();
			campaign.setId(campaignid);
			//powerBank.setId();
			
			campaignPowerBank.setCampaign(campaign);
			campaignPowerBank.setPowerBank(powerBank);
			
			campaignPowerBankList.add(campaignPowerBank);
		}
		
		campaignPowerBankJpaRepository.saveAll(campaignPowerBankList);
		
		List<CampaignPowerBank> cpbList = campaignPowerBankJpaRepository.findByCampaignId(campaignid);
		
		return cpbList;
	}
	
	@GetMapping("uploadpowerbanks/{id}")
	public String uploadPowerbanktoCampaign(@PathVariable("id") long id, Model model) {
		model.addAttribute("campaignid", id);
		return "uploadpowerbanks";
	}
	
	@PostMapping("/uploadpowerbankexcel")
    public String excelPowerbanktoCampaign(@RequestParam("id") long id,@RequestParam("uploadfile") MultipartFile file, Model model) {
		model.addAttribute("campaignid", id);
		try {

    		Workbook workbook = new XSSFWorkbook(file.getInputStream());
     
    		Sheet sheet = workbook.getSheetAt(0);
    		Iterator<Row> rows = sheet.iterator();
    		
    		List<CampaignPowerBank> campaignPowerBankList = new ArrayList<>();
    		
    		int rowNumber = 0;
    		while (rows.hasNext()) {
    			Row currentRow = rows.next();
    			
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			}
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();

    			CampaignPowerBank campaignPowerBank = new CampaignPowerBank();
    			Campaign campaign = new Campaign();
    			campaign.setId(id);
    			campaignPowerBank.setCampaign(campaign);
    			
    			
    			int cellIndex = 0;
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = cellsInRow.next();
    				
    				if(cellIndex==0) { // ID
    					PowerBank powerBank = powerBankJpaRepository.findByPowerBankNo(currentCell.getStringCellValue());
    					campaignPowerBank.setPowerBank(powerBank);
    				} 
    				cellIndex++;
    			}
    			
    			
    			campaignPowerBankList.add(campaignPowerBank);
    		}
    		
    		// Close WorkBook
    		workbook.close();
			
    		campaignPowerBankJpaRepository.saveAll(campaignPowerBankList);
    		
	      model.addAttribute("message", "File uploaded successfully!");
	    } catch (Exception e) {
	      model.addAttribute("message", "Fail! -> uploaded filename: " + file.getOriginalFilename());
	    }
	    
		return "uploadpowerbanks";
    }
	
	@GetMapping("addLocationtoCampaign")
	public String addLocationtoCampaign(Long campaignid,Long locationid, Model model) {
		
		Campaign campaign = campaignRepository.findById(campaignid)
				.orElseThrow(() -> new IllegalArgumentException("Invalid campaign Id:" + campaignid));
		
		CampaignLocation campaignLocation = new CampaignLocation();
		
		Optional<Location> location = locationJpaRepository.findById(locationid);
		
		campaignLocation.setCampaign(campaign);
		campaignLocation.setLocation(location.get());
		campaignLocationJpaRepository.save(campaignLocation);
		
		model.addAttribute("campaign", campaign);
			
		List<Location> locationList = locationJpaRepository.findAll();
		
		model.addAttribute("locations", locationList);
		

		List<CampaignLocation> cpbList = campaignLocationJpaRepository.findByCampaignId(campaignid);
		model.addAttribute("cpbList", cpbList);
		return "campaign-location";
	}
	
	@RequestMapping("findShopByMerchantId")
	@ResponseBody
	public List<Shop> findShopByMerchantId(long merchantId) {
		List<Shop> shop = shopJpaRepository.findByMerchantIdAndIsDeletedFalseOrderByShopNameAsc(merchantId);
		return shop;
	}

	@RequestMapping("findCabinetByShopId")
	@ResponseBody
	public List<Cabinet> findCabinetByShopId(long shopId) {
		List<Cabinet> cabinet = caibnetJpaRepository.findByShopId(shopId);
		return cabinet;
	}

	@RequestMapping("findPowerBankByCabinetNo")
	@ResponseBody
	public List<PowerBank> findPowerBankByCabinetNo(String cabinetNo) {
		List<CabinetPosition> cabinetPositions = caibnetPositionJpaRepository.findByCabinetNo(cabinetNo);
		List<String> powerBankIds = new ArrayList<String>();
		List<PowerBank> powerbanks = new ArrayList<PowerBank>();

		for (CabinetPosition cabinetPosition : cabinetPositions) {
			if (null != cabinetPosition.getPowerBank())
				powerBankIds.add(cabinetPosition.getPowerBank());
		}
		powerbanks = powerBankJpaRepository.findByPowerBankNoIn(powerBankIds);

		return powerbanks;
	}
	
	@RequestMapping("findPowerBankByShopId")
	@ResponseBody
	public List<PowerBank> findPowerBankByShopId(long shopId) {
		
		List<Cabinet> cabinets = caibnetJpaRepository.findByShopId(shopId);
		
		List<CabinetPosition> cabinetPositions = new ArrayList<CabinetPosition>(); 
		for (Cabinet cabinet : cabinets) {
			cabinetPositions.addAll(caibnetPositionJpaRepository.findByCabinetNo(cabinet.getCabinetNo()));
		}
		
		List<String> powerBankIds = new ArrayList<String>();
		List<PowerBank> powerbanks = new ArrayList<PowerBank>();

		for (CabinetPosition cabinetPosition : cabinetPositions) {
			if (null != cabinetPosition.getPowerBank())
				powerBankIds.add(cabinetPosition.getPowerBank());
		}
		powerbanks = powerBankJpaRepository.findByPowerBankNoIn(powerBankIds);

		return powerbanks;
	}

	@GetMapping("pbusage")
	public String getPowerBankUsage(Model model) {
		return "powerbankusage";
	}

	@PostMapping("pbusage")
	public String showPowerBankUsage(Model model, @Param("pbnumber") String pbnumber,
			@Param("startdate") String startdate, @Param("enddate") String enddate) {

		List<Order> orderList = orderJpaRepository.findByPowerBankNoAndCreateTimeBetween(pbnumber, new Date(startdate),
				new Date(enddate));

		model.addAttribute("orders", orderList);

		return "powerbankusage";
	}

	
	private JSONObject getUsageMetrics(List<Order> orderList, List<String> powerBankList, Date startDate, Calendar end ) {
		Map<String,JSONObject> metricMap = new HashMap<>();
		
		JSONObject returnObj = new JSONObject();
		
		SimpleDateFormat df = new SimpleDateFormat("MMM yyyy");
		JSONArray headerArray = new JSONArray();
		headerArray.put("PowerBankNo");
		headerArray.put("Total");
		
		for (String powerBank : powerBankList) {
			JSONObject currOjb = new JSONObject();
			currOjb.put("PowerBankNo", powerBank);
			currOjb.put("Total", 0);
			Calendar current = Calendar.getInstance();
			current.setTime(new Date(startDate.getTime()));
			
			for (; current.before(end); current.add(Calendar.MONTH, 1)) {
				currOjb.put(df.format(current.getTime()),0);
			}
			
			metricMap.put(powerBank, currOjb);
		}
		
		
		for (Order order : orderList) {
			JSONObject currOjb = metricMap.get(order.getPowerBankNo());
			if(!headerArray.toString().contains(df.format(order.getCreateTime()))) {
				headerArray.put(df.format(order.getCreateTime()));
			}
			currOjb.put(df.format(order.getCreateTime()), ((int)currOjb.get(df.format(order.getCreateTime()))+1));
			currOjb.put("Total", (int)currOjb.get("Total")+1);
		}
		
		System.out.println(metricMap.size());
		
		Collection<JSONObject> metricJsonList = metricMap.values();
		
		JSONArray dataArray = new JSONArray();
		
		for (JSONObject jsonObject : metricJsonList) {
			dataArray.put(jsonObject);
		}
		
		returnObj.put("header", headerArray);
		returnObj.put("data", dataArray);
		
		return returnObj;
	}
	
	
}
