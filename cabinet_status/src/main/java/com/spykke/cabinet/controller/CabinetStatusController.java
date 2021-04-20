package com.spykke.cabinet.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.spykke.cabinet.entity.BodyDto;
import com.spykke.cabinet.entity.Cabinet;
import com.spykke.cabinet.entity.CabinetLink;
import com.spykke.cabinet.entity.OutputDto;
import com.spykke.cabinet.entity.Shop;
import com.spykke.cabinet.repository.CabinetJpaRepository;
import com.spykke.cabinet.repository.CabinetLinkJpaRepository;
import com.spykke.cabinet.repository.ShopJpaRepository;


@Controller
public class CabinetStatusController {
	
	@Autowired
	CabinetLinkJpaRepository  cabinetLinkJpaRepository;
	
	@Autowired
	CabinetJpaRepository  cabinetJpaRepository;
	
	@Autowired
	ShopJpaRepository shopJpaRepository;
	
	
	@RequestMapping("citycabinetstatus")
	public void citycabinetstatus(@RequestBody BodyDto body,HttpServletResponse response) throws IOException {
		
		Calendar start = Calendar.getInstance();
		start.setTime(body.getStartDate());

		Calendar end = Calendar.getInstance();
		end.setTime(body.getEndDate());
		end.add(Calendar.DATE, 1);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		
		if(start.after(end)) {
			response.sendError(404, "startDate greater then endDate");
			return;
		}
		
		if(null == body.getCity() || body.getCity().isEmpty()) {
			response.sendError(404, "enter city name");
			return;
		}
		
		List<Shop>  shopList = null;
		
		if(null!=body.getCity()) {
			 shopList = shopJpaRepository.findAllByCityIgnoreCaseContaining(body.getCity());
		}
		if(null == shopList || shopList.isEmpty()) {
			response.sendError(404, "shop not available");
		}
		
		List<Cabinet> cabinetList = new ArrayList<Cabinet>();
		for (Shop shop : shopList) {
			cabinetList.addAll(cabinetJpaRepository.findAllByShop(shop));
		}
		
		List<OutputDto> outputDtos = new ArrayList<OutputDto>();

		while(start.before(end)) {
			List<OutputDto> currDateDtos = new ArrayList<OutputDto>();
			for (Cabinet cabinet : cabinetList) {
				currDateDtos.addAll(getCabinetLinks(cabinet.getCabinetNo(), start, cabinet,false));
			}
			
			
			OutputDto currOutputDto = new OutputDto();
			currOutputDto.setCity(body.getCity());
			currOutputDto.setDate(dateFormat.format(start.getTime()));
			float onlineCount = 0;
			float offlineCount = 0;
			for (OutputDto outputDto : currDateDtos) {
				if("Online".equalsIgnoreCase(outputDto.getStatus())) {
					onlineCount++;
				}else {
					offlineCount++;
				}
			}
			currOutputDto.setOffline(decimalFormat.format(offlineCount*100/(offlineCount+onlineCount))+"%");
			currOutputDto.setOnline(decimalFormat.format(onlineCount*100/(offlineCount+onlineCount))+"%");
			
			outputDtos.add(currOutputDto);
			start.add(Calendar.DATE, 1);
		}
		
		response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=status_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
		
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        
        String[] csvHeader = {"Date", "City", "Online", "Offline"};
        String[] nameMapping = {"Date", "city", "online", "offline"};
        
        csvWriter.writeHeader(csvHeader);
         
        for (OutputDto dto : outputDtos) {
            csvWriter.write(dto, nameMapping);
        }
        csvWriter.close();
	}
	
	@RequestMapping("shopcabinetstatus")
	public void shopcabinetstatus(@RequestBody BodyDto body,HttpServletResponse response) throws IOException {
		
		Calendar start = Calendar.getInstance();
		start.setTime(body.getStartDate());

		Calendar end = Calendar.getInstance();
		end.setTime(body.getEndDate());
		end.add(Calendar.DATE, 1);
		
		if(start.after(end)) {
			response.sendError(404, "startDate greater then endDate");
			return;
		}
		
		if(null == body.getShop() || body.getShop().isEmpty()) {
			response.sendError(404, "enter shop name");
			return;
		}
		
		List<Shop>  shopList = null;
		
		if(null!=body.getShop()) {
			 shopList = shopJpaRepository.findAllByShopNameIgnoreCaseContaining(body.getShop());
		}
		if(null == shopList || shopList.isEmpty()) {
			response.sendError(404, "shop not available");
		}
		
		List<Cabinet> cabinetList = new ArrayList<Cabinet>();
		for (Shop shop : shopList) {
			cabinetList.addAll(cabinetJpaRepository.findAllByShop(shop));
		}
		
		List<OutputDto> outputDtos = new ArrayList<OutputDto>();
		
		for (Cabinet cabinet : cabinetList) {
			start.setTime(body.getStartDate());
			while(start.before(end)) {
				outputDtos.addAll(getCabinetLinks(cabinet.getCabinetNo(), start, cabinet,false));
				start.add(Calendar.DATE, 1);
			}
		}
		
		response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=status_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
		
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"StationId",  "Merchant", "BranchName","Date", "Online", "Offline"};
        String[] nameMapping = {"stationId", "merchantName", "shopName","Date", "online", "offline"};
         
        csvWriter.writeHeader(csvHeader);
         
        for (OutputDto dto : outputDtos) {
            csvWriter.write(dto, nameMapping);
        }
        csvWriter.close();
	}
	
	@RequestMapping("cabinetstatus")
	public void cabinetstatus(@RequestBody BodyDto body,HttpServletResponse response) throws IOException {
		
		Calendar start = Calendar.getInstance();
		start.setTime(body.getStartDate());

		Calendar end = Calendar.getInstance();
		end.setTime(body.getEndDate());
		end.add(Calendar.DATE, 1);
		
		if(start.after(end)) {
			response.sendError(404, "startDate greater then endDate");
		}
		
		List<OutputDto> outputDtos = new ArrayList<OutputDto>();
		
		for (String cabinetNo : body.getStations()) {
			start.setTime(body.getStartDate());
			Cabinet cabinet = cabinetJpaRepository.findByCabinetNo(cabinetNo) ;
			while(start.before(end)) {
				outputDtos.addAll(getCabinetLinks(cabinetNo, start, cabinet, true));
				start.add(Calendar.DATE, 1);
			}
		}
		
		response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=status_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
		
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"SlNo", "StationId", "Status", "MerchantId", "Merchant", "BranchId", "BranchName", "Date", "Time"};
        String[] nameMapping = {"slNo", "stationId", "status", "merchantId", "merchantName", "shopId", "shopName", "Date", "Time"};
         
        csvWriter.writeHeader(csvHeader);
         
        for (OutputDto dto : outputDtos) {
            csvWriter.write(dto, nameMapping);
        }
        csvWriter.close();
	}

	private List<OutputDto> getCabinetLinks(String cabinetNo, Calendar date, Cabinet cabinet, boolean fullReport) {
		Calendar startRange = Calendar.getInstance();
		startRange.setTime(date.getTime());
		startRange.set(Calendar.HOUR, 0);
		startRange.set(Calendar.MINUTE, 0);
		startRange.set(Calendar.SECOND, 0);
		
		Calendar endRange = Calendar.getInstance();
		endRange.setTime(date.getTime());
		endRange.set(Calendar.HOUR, 23);
		endRange.set(Calendar.MINUTE, 59);
		endRange.set(Calendar.SECOND, 59);
		
		List<CabinetLink> cabinetLinks = cabinetLinkJpaRepository.findAllByCabinetNoAndCreateTimeBetweenOrderByCreateTimeAsc(cabinetNo, startRange.getTime(), endRange.getTime());
		
		if(cabinetLinks == null || cabinetLinks.isEmpty()) {
			return new ArrayList<>();
		}
		
		return createData(cabinetLinks,date,cabinet, fullReport);
	}
	
	private List<OutputDto> createData(List<CabinetLink> cabinetLinks, Calendar date, Cabinet cabinet, boolean fullReport) {
		
		Calendar startRange = Calendar.getInstance();
		startRange.setTime(date.getTime());
		startRange.set(Calendar.HOUR, 6);
		startRange.set(Calendar.MINUTE, 30);
		startRange.set(Calendar.SECOND, 0);
		
		Calendar endRange = Calendar.getInstance();
		endRange.setTime(date.getTime());
		endRange.add(Calendar.DATE, 1);
		endRange.set(Calendar.HOUR, 0);
		endRange.set(Calendar.MINUTE, 30);
		endRange.set(Calendar.SECOND, 0);
		try {
			
			if(null!=cabinet.getShop()) {
				SimpleDateFormat shopTimeFormat = new SimpleDateFormat("hh:mma");
				String shopTiming = cabinet.getShop().getOpenTime();
				if(null!=shopTiming && !shopTiming.isEmpty()) {
					String openTime = shopTiming.split("\\|")[0].split("-")[0];
					String closeTime = shopTiming.split("\\|")[0].split("-",-1)[1];
					
					if(null!=openTime && !openTime.isEmpty()) {
						Date openDate = shopTimeFormat.parse(openTime);
						startRange.set(Calendar.HOUR, openDate.getHours());
						startRange.set(Calendar.MINUTE, openDate.getMinutes());
					}
					
					if(null!=closeTime && !closeTime.isEmpty()) {
						Date closeDate = shopTimeFormat.parse(closeTime);
						//System.out.println(closeTime);
						//System.out.println(closeDate.getHours());
						//System.out.println(closeDate.getMinutes());
						//System.out.println();
						endRange.set(Calendar.HOUR, closeDate.getHours());
						endRange.set(Calendar.MINUTE, closeDate.getMinutes());
						if(closeDate.getHours()>0) {
							endRange.add(Calendar.DATE, -1);
						}
					}
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		
		String currentStatus = "OFFLINE";
		
		Iterator<CabinetLink> cabinetLinkIterator = cabinetLinks.iterator();
		
		CabinetLink currCabinetLink = cabinetLinkIterator.next();
		Calendar currentCabinetTime = Calendar.getInstance();
		currentCabinetTime.setTime(currCabinetLink.getCreateTime());
		
		List<OutputDto> outputDtos = new ArrayList<OutputDto>();
		int slNo = 1; 
		float onlineCount = 0;
		float offlineCount = 0;
				
		
		while(startRange.before(endRange)||startRange.equals(endRange)) {
			
			OutputDto outputDto = new OutputDto();
			outputDto.setSlNo(slNo++);
			outputDto.setDate(dateFormat.format(startRange.getTime()));
			outputDto.setTime(timeFormat.format(startRange.getTime()));
			if(null!=cabinet.getShop()) {
				outputDto.setShopId(String.valueOf(cabinet.getShop().getId()));
				outputDto.setShopName(cabinet.getShop().getShopName());
				outputDto.setCity(cabinet.getShop().getCity());
				if(null!=cabinet.getShop().getMerchant()) {
					outputDto.setMerchantId(String.valueOf(cabinet.getShop().getMerchant().getId()));
					outputDto.setMerchantName(cabinet.getShop().getMerchant().getName());
				}
			} 
			outputDto.setStationId(cabinet.getCabinetNo());
			
			if(startRange.after(currentCabinetTime)) {
				while(startRange.after(currentCabinetTime)) {
					if(currCabinetLink.isState()) {
						currentStatus = "ONLINE";
					}else {
						currentStatus = "OFFLINE";
					}
					if(cabinetLinkIterator.hasNext()) {
						currCabinetLink = cabinetLinkIterator.next();
						currentCabinetTime.setTime(currCabinetLink.getCreateTime());
					}else {
						break;
					}
				}
			}
			outputDto.setStatus(currentStatus);
			switch (currentStatus) {
			case "ONLINE":
				onlineCount++;
				break;
			case "OFFLINE":
				offlineCount++;
				break;
			}
			if(fullReport)
				outputDtos.add(outputDto);
			startRange.add(Calendar.MINUTE, 30);
		}
		

		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		OutputDto outputDto = new OutputDto();
		if(fullReport) {
			outputDto.setShopId("Offline");
			outputDto.setShopName(decimalFormat.format(offlineCount*100/(offlineCount+onlineCount))+"%");
			outputDto.setMerchantId("Online");
			outputDto.setMerchantName(decimalFormat.format(onlineCount*100/(offlineCount+onlineCount))+"%");
			outputDtos.add(outputDto);
			outputDtos.add(new OutputDto());
		}else {
			outputDto.setStationId(cabinet.getCabinetNo());
			outputDto.setDate(dateFormat.format(startRange.getTime()));
			outputDto.setOffline(decimalFormat.format(offlineCount*100/(offlineCount+onlineCount))+"%");
			outputDto.setOnline(decimalFormat.format(onlineCount*100/(offlineCount+onlineCount))+"%");
			if(onlineCount >= offlineCount) {
				outputDto.setStatus("Online");
			}else {
				outputDto.setStatus("Offline");
			}
			
			if(null!=cabinet.getShop()) {
				outputDto.setShopId(String.valueOf(cabinet.getShop().getId()));
				outputDto.setShopName(cabinet.getShop().getShopName());
				outputDto.setCity(cabinet.getShop().getCity());
				if(null!=cabinet.getShop().getMerchant()) {
					outputDto.setMerchantId(String.valueOf(cabinet.getShop().getMerchant().getId()));
					outputDto.setMerchantName(cabinet.getShop().getMerchant().getName());
				}
				
			} 
			outputDtos.add(outputDto);
		}
		return outputDtos;
	}
	

}
