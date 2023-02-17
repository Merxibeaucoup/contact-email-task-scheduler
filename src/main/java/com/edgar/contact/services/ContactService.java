package com.edgar.contact.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.contact.exceptions.ContactAlreadyExistException;
import com.edgar.contact.exceptions.ContactDoesNotExistException;
import com.edgar.contact.models.Contact;
import com.edgar.contact.repositories.ContactRepository;

@Service
public class ContactService {
	
	
	@Autowired
	private ContactRepository repo;
	
	
	
	/* find by email **/
	public Contact getContactByEmail(String email) {
		return repo.findByEmail(email).orElseThrow(()-> new ContactDoesNotExistException("Contact not found with email :" + email));
		}
	
	
	
	
	/* create **/
	public Contact addNew (Contact contact) {
		
		if(!isExists(contact.getEmail())) {
			return repo.save(contact);
		}
		
		else throw new ContactAlreadyExistException("A contact already exists with the given Email: "+ contact.getEmail());
			
		 
	}
	
	/*get all **/
	public List<Contact> getAll(){
		return repo.findAll();
	}
	
	
	
	/*get one by id  **/
	public Contact getOneById(long id) {
		
		if(isExists(id)) {
			return repo.findById(id).get();
		}
		else {
			throw new ContactDoesNotExistException("contact not found with id : "+id);
		}
		
	}
	
	/*update one by id **/
	public Contact updateOneById(long id, Contact contact) {
		
		if(isExists(id)) {
			repo.findById(id);
			contact.setId(id);
			return repo.save(contact);
			
		}
		throw new ContactDoesNotExistException("contact not found with id : "+id);
		
		
		
	}
	
	/* delete one by id **/
	public void deleteOneById(long id) {
		
		if(isExists(id)) {
			repo.deleteById(id);
		}
		throw new ContactDoesNotExistException("contact not found with id : "+id);
		
		
	}
	
	
	
	/*  Contact Utils **/
	
	
	
	/* check email**/
	private boolean isExists(String email) {
		if(repo.existsByEmail(email)) {
			return true;
		}
		
		else return false;
	}
	
	/* check id**/
	private boolean isExists(Long id) {
		if(repo.existsById(id)) {
			return true;
		}
		
		else return false;
	}
	
	
	
	

}
