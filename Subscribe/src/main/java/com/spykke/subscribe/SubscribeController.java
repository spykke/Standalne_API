package com.spykke.subscribe;

import java.util.Date;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class SubscribeController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SubscribeService subscribeService;
	
	
	@RequestMapping("subscribe")
	public ResponseEntity<SubscribeEntity> send(@RequestBody SubscribeEntity user) {

		user.setEmailSent(true);
		user.setCreatedAt(new Date());
		user.setType("subscription");
		
		String msg = "Hi,\n\r";
		msg += "During Subscription we have received below information from the following user\n\r";
		msg += "Date: "+user.getCreatedAt();
		msg += "\n\rName: "+ user.getName() +"\n\r"+ "Email: "+ user.getEmail();
		msg += "\n\r\n\rThank & Regards,";
		msg += "\n\rSpykke Innovation Development Team";

		
		SimpleMailMessage email = new SimpleMailMessage();
		
		//email.setTo("info@spykke.com");
		email.setTo("arindam@spykke.com");
		email.setSubject("Subscription request from "+ user.getEmail());
		email.setText(msg);
		
		
		try {
			mailSender.send(email);
		} catch (MailException mailException) {
			System.out.println(mailException);
			user.setEmailSent(false);
		}
		
		SubscribeEntity updatedUser = subscribeService.save(user);
		
		return new ResponseEntity<SubscribeEntity>(updatedUser, HttpStatus.OK) ;
	}

	@RequestMapping("contactus")
	public ResponseEntity<SubscribeEntity> contactus(@RequestBody SubscribeEntity user) {

		user.setEmailSent(true);
		user.setCreatedAt(new Date());
		user.setType("contactus");
		
		String msg = "Hi,\n\r";
		msg += "In Contact Us Form,  we have received below information from the following user\n\r";
		msg += "Date: "+user.getCreatedAt();
		msg += "\n\rName: "+ user.getName() +"\n\r"+ "Email: "+ user.getEmail();
		msg += "\n\r"+ "Phone: "+ user.getPhone()+"\n\r"+ "Message: "+ user.getMessage();
		msg += "\n\r\n\rThank & Regards,";
		msg += "\n\rSpykke Innovation Development Team";

		SimpleMailMessage email = new SimpleMailMessage();
		
		//email.setTo(new String[]{"info@spykke.com", "support@spykke.com"});
		email.setTo(new String[]{"arindam@spykke.com"});
		email.setSubject("Contact Us request from "+ user.getEmail());
		email.setText(msg);
		
		try {
			mailSender.send(email);
		} catch (MailException mailException) {
			System.out.println(mailException);
			user.setEmailSent(false);
		}
		
		SubscribeEntity updatedUser = subscribeService.save(user);
		
		return new ResponseEntity<SubscribeEntity>(updatedUser, HttpStatus.OK) ;
	}

}
