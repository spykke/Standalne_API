package com.spykke.internal.adcampaign.controller.ui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spykke.internal.adcampaign.repository.ReportDataJpaRepository;

@Controller
public class ReportDataController {
	
	@Autowired
	ReportDataJpaRepository reportDataJpaRepository;

	@GetMapping(value = "/reportdata") 
    public String sample(Model model) { 
		model.addAttribute("datalist",reportDataJpaRepository.findAll());
		return "reportdata";
    }
	
	@RequestMapping("/assets/reports/{fileName}")
	public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("fileName") String fileName) throws IOException {

		//This config is for Production
		String EXTERNAL_FILE_PATH = "/home/ubuntu/campaign/src/main/resources/static/assets/reports/";
		
		//This config is for Local
		//String EXTERNAL_FILE_PATH = "/home/administrator/STS_Workspace/source_codes/Campaign/adcampaign/src/main/resources/static/assets/reports/";
		
		String path = ResourceUtils.getFile(EXTERNAL_FILE_PATH).getAbsolutePath();
		
		System.out.println("fileName");
		System.out.println(fileName);
		System.out.println(path);
		File file = new File(EXTERNAL_FILE_PATH + fileName);
		if (file.exists()) {
			System.out.println("file exists");
			System.out.println(file.getName());
			//get the mimetype
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}

			response.setContentType(mimeType);

			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

			response.setContentLength((int) file.length());

			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

			FileCopyUtils.copy(inputStream, response.getOutputStream());

		}
	}

}
