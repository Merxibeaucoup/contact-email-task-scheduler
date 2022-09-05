package com.edgar.contact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.contact.models.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>{

}
