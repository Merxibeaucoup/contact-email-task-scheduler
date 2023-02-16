package com.edgar.contact.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edgar.contact.exceptions.ContactDoesNotExistException;
import com.edgar.contact.models.Contact;
import com.edgar.contact.repositories.ContactRepository;
import com.edgar.contact.services.ContactService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/contact")
public class ContactController {
	

	@Autowired
	private ContactService contactService;
	

	@Autowired
	private ContactRepository contactRepository;
	
	
	/*
	 * This a test 
	 */
	@GetMapping("/test")
	public ResponseEntity<String> sayHello(){
		return ResponseEntity.ok("Helloo from the secured endpoint");
	}
	
	
	
	//create 
	@PostMapping("/new")
	public ResponseEntity<Contact> create( @Valid @RequestBody Contact contact){
		Contact newContact = contactService.addNew(contact);
		return ResponseEntity.ok(newContact);
	}
	
	//get all
	@GetMapping
	public ResponseEntity<List<Contact>> getAllContacts(){
		return ResponseEntity.ok(contactService.getAll());
	}
	
	// get one by id 
	@GetMapping("/{id}")
	public ResponseEntity<Contact> getOneContactById(@PathVariable long id )throws ContactDoesNotExistException{
		return ResponseEntity.ok(contactService.getOneById(id));
	}
	
	
	@GetMapping("/email")
	public ResponseEntity<Contact>  getOneContactByEmail(@RequestParam String email) throws ContactDoesNotExistException{
		return  ResponseEntity.ok(contactService.getContactByEmail(email));
	}
	
	
	//update one by id 
	@PutMapping("/{id}")
	public ResponseEntity<Contact> updateOneContact( @PathVariable long id, @RequestBody Contact contact){
		return ResponseEntity.ok(contactService.updateOneById(id, contact));
		
	}
	

	
	
	//delete one by id 
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOneContact(@PathVariable long id){
		return contactRepository.findById(id)
		           .map(record -> {
		               contactRepository.deleteById(id);
		               return ResponseEntity.ok().build();
		           }).orElse(ResponseEntity.notFound().build());
	}

}
