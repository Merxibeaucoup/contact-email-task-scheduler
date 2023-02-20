package com.edgar.contact.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import com.edgar.contact.auth.AuthenticationService;
import com.edgar.contact.models.Contact;
import com.edgar.contact.models.user.User;
import com.edgar.contact.repositories.ContactRepository;
import com.edgar.contact.services.ContactService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/contact")
public class ContactController {
	

	@Autowired
	private ContactService contactService;
	
	
	/*not needed anymore ... used @authentication principal */
	@Autowired
	private AuthenticationService auServ;
	
	
	

	@Autowired
	private ContactRepository contactRepository;
	
	
	/*
	 * This a test 
	 */
	@GetMapping("/test")
	public ResponseEntity<String> sayHello(){
		return ResponseEntity.ok("Helloo from the secured endpoint");
	}
	
	
	
	/*create endpoint  **/
	@PostMapping("/new")
	public ResponseEntity<Contact> create( @Valid @RequestBody Contact contact, @AuthenticationPrincipal User user) {
//		User loggedInUser = auServ.getUserByEmail(user.getEmail());
		Contact newContact = contactService.addNew(contact,user);
		return ResponseEntity.ok(newContact);
	}
	
	
	/*contacts belonging to logged in user endpoint  **/
	@GetMapping("/all/user")
	public ResponseEntity<List<Contact>> getUserContacts(@AuthenticationPrincipal User user){			
		return ResponseEntity.ok(contactService.getAllAssignedToUser(user));
	}
	
	
	/*get all contacts in the system  endpoint  .....might get rid of this   **/
	@GetMapping("/all")
	public ResponseEntity<List<Contact>> getAllContacts(@AuthenticationPrincipal User user ){
		user.setId(user.getId());		
		return ResponseEntity.ok(contactService.getAll());
	}
	
	/* get one by id endpoint  **/
	@GetMapping("/{id}")
	public ResponseEntity<Contact> getOneContactById(@PathVariable long id ){
		return ResponseEntity.ok(contactService.getOneById(id));
	}
	
	/*get one by email endpoint  **/
	@GetMapping("/email")
	public ResponseEntity<Contact>  getOneContactByEmail(@RequestParam String email) {
		return  ResponseEntity.ok(contactService.getContactByEmail(email));
	}
	
	
	/*update one by id endpoint  **/
	@PutMapping("/{id}")
	public ResponseEntity<Contact> updateOneContact( @PathVariable long id, @RequestBody Contact contact){
		return ResponseEntity.ok(contactService.updateOneById(id, contact));
		
	}
	

	
	
	/*delete one by id  endpoint  **/
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOneContact(@PathVariable long id) {
		return contactRepository.findById(id)
		           .map(record -> {
		               contactRepository.deleteById(id);
		               return ResponseEntity.ok().build();
		           }).orElse(ResponseEntity.notFound().build());
	}

}
