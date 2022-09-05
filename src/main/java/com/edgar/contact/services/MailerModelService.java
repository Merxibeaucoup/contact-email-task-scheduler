package com.edgar.contact.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
import com.edgar.contact.repositories.ContactRepository;
import com.edgar.contact.repositories.MailerModelRepository;

@Service
public class MailerModelService {
	
	
	
	
	@Autowired
	private MailerModelRepository mailerModelRepository; //declare MailerModelRepository
	
	
	@Autowired
	private JavaMailSender mailSender; //declare javaMailsender
	
	@Autowired
	private ContactRepository contactRepository; //declare contactRepository to use .findAll() to get all contacts
	
	
	// This method loops through all the contacts and finds if there is a birthday today
	@Scheduled(cron = "0 00 10 * * *")  //run @10am everyday......second, minute, hour, day, month, weekday
	public void checkforBirthday() {
		List<Contact> filter_Contacts = contactRepository.findAll()
				.stream()
				.filter(c -> c.getBirthday() != null  //if birthday of contact not null
				&& c.getBirthday().getDayOfMonth()== LocalDate.now().getDayOfMonth() // compare if day == localdate day
				&& c.getBirthday().getMonth().getValue() == LocalDate.now().getMonth().getValue()) // compare if month === localdate month
				.collect(Collectors.toList()); // make a list of those contacts 
		
		if(filter_Contacts.size() > 0) // if there is indeed such contact
		{
			filter_Contacts.stream()
			.forEach(e ->{
				
				MailerModel mailerModel = new MailerModel(); // mailerModel obj
				
				mailerModel.setEmailFrom("eddiebrrrt@gmail.com"); //mail from me
				mailerModel.setEmailTo(e.getEmail()); // mail to e
				
				mailerModel.setSubject("Happy Birthday :)"); // subject
				
                mailerModel.setMessage("\uD83E\uDD73\uD83E\uDD73\uD83E\uDD73\uD83E\uDD73\uD83E\uDD73 Happy" + (LocalDate.now().getYear() - e.getBirthday().getYear())+" Birthday "+ e.getFirstname()
                +"  \uD83C\uDF89\uD83C\uDF89\uD83C\uDF89\uD83C\uDF89 ," +" Have a wonderful day on this special day ;)"); //message

				sendEmail(mailerModel); // invoke sendEmail method
				
				
			});
		}
		
	}
	
	
	public MailerModel sendEmail(MailerModel mailerModel) {
		
		// message time
		mailerModel.setSentDateTime(LocalDateTime.now()); 
		
		try {
			SimpleMailMessage message = new SimpleMailMessage(); // use simplemailmessage to compile message
			message.setFrom(mailerModel.getEmailFrom());
			message.setTo(mailerModel.getEmailTo());
			message.setSubject(mailerModel.getSubject());
			message.setText(mailerModel.getMessage());
			mailSender.send(message); // send the given simple massage 
			
			mailerModel.setStatus(StatusEmail.SENT); // if successful
		} catch( MailException e) {
			mailerModel.setStatus(StatusEmail.ERROR); // if not successful catch error
			
		}
				
		
			return mailerModelRepository.save(mailerModel);
		
		
	}

}
