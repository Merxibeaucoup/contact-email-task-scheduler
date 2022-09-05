package com.edgar.contact.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.contact.models.Contact;
import com.edgar.contact.repositories.ContactRepository;

@Service
public class ContactService {
	
	//declare  contact repository 
	@Autowired
	private ContactRepository repo;
	
	
	
	
	//create 
	public Contact addNew (Contact contact) {
		return repo.save(contact);
	}
	
	//get all 
	public List<Contact> getAll(){
		return repo.findAll();
	}
	
	//get one by id 
	public Contact getOneById(long id) {
		return repo.findById(id).get();
	}
	
	//update one by id 
	public Contact updateOneById(long id, Contact contact) {
		repo.findById(id);
		contact.setId(id);
		
		return repo.save(contact);
	}
	
	//delete one by id 
	public void deleteOneById(long id) {
		repo.deleteById(id);
	}
	

}
