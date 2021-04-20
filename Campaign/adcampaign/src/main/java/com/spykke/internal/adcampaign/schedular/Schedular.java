package com.spykke.internal.adcampaign.schedular;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.spykke.internal.adcampaign.entity.ReportData;
import com.spykke.internal.adcampaign.repository.CaibnetJpaRepository;
import com.spykke.internal.adcampaign.repository.ReportDataJpaRepository;
import com.spykke.internal.commondb.entity.Cabinet;

@Service
public class Schedular {

	@Autowired
	CaibnetJpaRepository caibnetJpaRepository;
	
	@Autowired
	ReportDataJpaRepository reportDataJpaRepository;
	
	//This config is for Production
	@Scheduled(cron = "0 0 8,12 * * ?")
	
	//This config is for Local
	//@Scheduled(cron = "0 22 12,23 * * ?")
	public void saveCabinetStatus(){
		Date currDate = new Date();
		DateFormat dbdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
		TimeZone tzIN = TimeZone.getTimeZone("Asia/Calcutta");
		df.setTimeZone(tzIN);
		System.out.println("Schedular "+df.format(currDate));
		
		List<Cabinet> cabinetList;
		try {
			String fileName = "cabinetstatus_"+df.format(currDate)+".csv";
			String resourcePath = "/assets/reports/";
			String path = ResourceUtils.getFile("src/main/resources/static/").getAbsolutePath()+resourcePath;
			System.out.println(path+fileName);
			File file = new File(path+fileName);
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	
	        bw.write("SlNo;CabinetId;CabinetNumber;Status;BranchId;BranchName;BranchCity;BranchArea;BranchAddress;MerchantId;MerchantName;MerchantAddress");
	        bw.newLine();
	        
	        int index = 1;
	        
	        	cabinetList = caibnetJpaRepository.findByShopNotNull();//.toList();
	        			
	        	for (Cabinet cabinet : cabinetList) {
		        	String line = ""+index++;
		        	line += ";"+cabinet.getId();
		        	line += ";"+cabinet.getCabinetNo();
		        	line += ";"+((cabinet.getState()) ? "Online" : "Offline");
		        	if(null!=cabinet.getShop()) {
			        	line += ";"+cabinet.getShop().getId();		
			        	line += ";"+cabinet.getShop().getShopName();
			        	line += ";"+cabinet.getShop().getCity();
			        	line += ";"+cabinet.getShop().getArea();
			        	line += ";"+"\""+cabinet.getShop().getAddr()+"\"";
			        	if(null!=cabinet.getShop().getMerchant()){
			        		line += ";"+cabinet.getShop().getMerchant().getId();
			        		line += ";"+cabinet.getShop().getMerchant().getName();
			        		line += ";"+"\""+cabinet.getShop().getMerchant().getAddress()+"\"";
		        		}else {
		        			line +=";;;";
		        		}
		        	}else {
		    			line +=";;;;;;;;";
		    		}
		        	bw.write(line);
		        	bw.newLine();
				}
	        
	        bw.close();
	        fw.close();
	        
	        ReportData rd = new ReportData();
	        rd.setDate(dbdf.format(currDate));
	        rd.setFileName(fileName);
	        rd.setType("DailyCabinetStatus");
	        rd.setUrl(resourcePath+fileName);
	        reportDataJpaRepository.save(rd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
