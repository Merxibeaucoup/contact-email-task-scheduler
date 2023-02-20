package com.edgar.contact.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.contact.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	Optional<Contact> findByEmail(String email);
	
	Boolean existsByEmail(String email);

	List<Contact> findAllById(Long id);
	
	
	
	
	


}
