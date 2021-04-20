package com.spykke.subscribe.banner;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spykke.subscribe.banner.entity.AppUser;
import com.spykke.subscribe.banner.entity.BannerAd;
import com.spykke.subscribe.banner.entity.BannerStation;
import com.spykke.subscribe.banner.entity.TrackingBannerAd;
import com.spykke.subscribe.banner.repository.AppUserRepository;
import com.spykke.subscribe.banner.repository.BannerRepository;
import com.spykke.subscribe.banner.repository.BannerStationRepository;
import com.spykke.subscribe.banner.repository.ClientJpaRepository;
import com.spykke.subscribe.banner.repository.TrackingBannerRepository;
import com.spykke.subscribe.banner.repository.VideoBannerRepository;

@RestController
public class VideoBannerApiController {
	
	@Autowired
	VideoBannerRepository videoBannerService;
	
	@Autowired
	ClientJpaRepository clientService;
	
	@Autowired
	BannerStationRepository bannerStationRepository;
	
	@Autowired
	TrackingBannerRepository trackingBannerRepository;
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Autowired
	BannerRepository bannerRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/getadbystation",produces="application/json", consumes="application/json")
	public BannerAd getAdByStation(@RequestBody Map<String, String> req) {
		
		BannerStation bannerStation = bannerStationRepository.findByStationId(req.get("stationid").toString());
		
		return bannerStation.getBanner();
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/api/addbannertracking",produces="application/json", consumes="application/json")
	public ResponseEntity<String> addTrackingInfo(@RequestBody Map<String, String> req) {
		
		TrackingBannerAd ad = new TrackingBannerAd();
		ad.setStationId(req.get("stationid").toString());
		ad.setUserId(req.get("userid").toString());
		ad.setOrderId(req.get("orderid").toString());
		ad.setClientId(req.get("clientid").toString());
		ad.setClientEmail(req.get("clientemail").toString());
		ad.setTriggerStr(req.get("trigger").toString());
		ad.setBannerId(req.get("adid").toString());
		ad.setTriggerDate(new Date());
		
		if("ContactMe".equalsIgnoreCase(req.get("trigger").toString())) {
			try {
				AppUser user = appUserRepository.findById(Long.valueOf(req.get("userid"))).get();
				BannerAd bannerAd = bannerRepository.findById(Long.valueOf(req.get("adid"))).get();
				
				String msg = "Dear Client,\n\r";
				msg += "The following Spykke customer has requested to be contacted regarding your campign\n\r";
				msg += "Name: "+user.getNickname();
				msg += "\n\rPhone: "+ user.getPhone() +"\n\r"+ "Email: "+ user.getEmail();
				msg += "\n\r\n\rThank & Regards,";
				msg += "\n\rSpykke Innovation Team";

				SimpleMailMessage email = new SimpleMailMessage();
				email.setTo(req.get("clientemail").toString());
				email.setCc("ads@spykke.com");
				email.setSubject("Spykke-"+ bannerAd.getClient().getName() + "-" + bannerAd.getAdName());
				email.setText(msg);
				
				try {
					mailSender.send(email);
				} catch (MailException mailException) {
					System.out.println(mailException);
					return ResponseEntity.badRequest().body("Unable to send mail");
				} 
				
			} catch (Exception e) {
				// TODO: handle exception
				return ResponseEntity.badRequest().body("Incorrect parameters");
			}
		}
		trackingBannerRepository.save(ad);
		return ResponseEntity.ok("success");
	}
	
}
