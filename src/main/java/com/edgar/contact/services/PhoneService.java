package com.edgar.contact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.contact.models.Phone;
import com.edgar.contact.repositories.PhoneRepository;

@Service
public class PhoneService {
	
	
	// declare phone repository
	@Autowired
	private PhoneRepository phoneRepository;
	
	
	//create 
	public Phone addPhoneNumber(Phone phone) {
		return phoneRepository.save(phone);
	}
	
	
	//update one by id 
	public Phone updatePhoneNumberById(long id, Phone phone) {
		phoneRepository.findById(id);
		phone.setId(id);
		return phoneRepository.save(phone);
	}
	
	//delete
	public void deletePhoneNumberById(long id) {
		phoneRepository.deleteById(id);
	}
	
	

}
