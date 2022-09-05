package com.edgar.contact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.contact.models.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
