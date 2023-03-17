package com.edgar.contact.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.edgar.contact.enums.StatusEmail;
import com.edgar.contact.models.Contact;
import com.edgar.contact.models.MailerModel;
import com.edgar.contact.models.user.User;
import com.edgar.contact.repositories.ContactRepository;
import com.edgar.contact.repositories.MailerModelRepository;
import com.edgar.contact.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MailerModelService {
	
	
	
	
	@Autowired
	private MailerModelRepository mailerModelRepository; 
	
	
	@Autowired
	private JavaMailSender mailSender; 
	
	@Autowired
	private ContactRepository contactRepository; 
	
	@Autowired
	private UserRepository userRepository; 
	
	

	@Scheduled(cron = "1 * * * * *")  //run @10am everyday......second, minute, hour, day, month, weekday
	public void checkforBirthday() {
		List<Contact> filter_Contacts = contactRepository.findAll()
				.stream()
				.filter(c -> c.getBirthday() != null 
				&& c.getBirthday().getDayOfMonth()== LocalDate.now().getDayOfMonth() 
				&& c.getBirthday().getMonth().getValue() == LocalDate.now().getMonth().getValue())
				.collect(Collectors.toList());
		
		/* if there is a birthday today **/
		if(filter_Contacts.size() > 0) 
		{
			filter_Contacts.stream()
			.forEach(e -> {
				
				MailerModel mailerModel = new MailerModel();
				
				Optional<User> user = userRepository.findById(e.getUser().getId());
				
				String uSerEmail = user.get().getEmail();
				
				System.out.println(uSerEmail);
				
				mailerModel.setEmailFrom(uSerEmail); //mail from me
				mailerModel.setEmailTo(e.getEmail()); // mail to e						
				mailerModel.setSubject("Happy Birthday :)"); 				
                mailerModel.setMessage("\uD83E\uDD73\uD83E\uDD73\uD83E\uDD73\uD83E\uDD73\uD83E\uDD73 Happy " + (LocalDate.now().getYear() - e.getBirthday().getYear())+" Birthday "+ e.getFirstname()
                +"  \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89\uD83C\uDF89 ," +" Have a wonderful day on this special day ;)"); 

				sendEmail(mailerModel);
				
				
			});
		}
		
		log.info("----------------mailer running--------------");
		
	}
	
	
	public MailerModel sendEmail(MailerModel mailerModel) {
		
		// message time
		mailerModel.setSentDateTime(LocalDateTime.now()); 
		
		
		try {
			SimpleMailMessage message = new SimpleMailMessage(); 
			
			
			message.setFrom(mailerModel.getEmailFrom());
			message.setTo(mailerModel.getEmailTo());
			message.setSubject(mailerModel.getSubject());
			message.setText(mailerModel.getMessage());
			mailSender.send(message); 
			
			mailerModel.setStatus(StatusEmail.SENT); // if successful
			
			log.info("---Mesage Sent---");
		} catch( MailException e) {
			mailerModel.setStatus(StatusEmail.ERROR); // else
			log.info("---Mesage Sent---");
			
		}
				
		
			return mailerModelRepository.save(mailerModel);
		
		
	}

}
