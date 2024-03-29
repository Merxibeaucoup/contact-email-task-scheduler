package com.edgar.contact.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgar.contact.exceptions.ContactDoesNotExistException;
import com.edgar.contact.models.Contact;
import com.edgar.contact.models.user.User;
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
	public Contact addNew (Contact contact, User user) {
				
			contact.setUser(user);
			return repo.save(contact);
					 
	}
	
	//turn below to set
	
	/* get contacts of current user only  **/
	public List<Contact> getAllAssignedToUser(User user){
		return repo.findByUser(user);
		
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
	public Contact updateOneById(long id, Contact contact, User user) {
		
		if(isExists(id)) {
			repo.findById(id).get();
			contact.setId(id);	
			contact.setUser(user);
			contact.setDate(new Date());
			return repo.save(contact);
			
		}
		
		else {
		throw new ContactDoesNotExistException("contact not found with id : "+id);
		
		}
		
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
