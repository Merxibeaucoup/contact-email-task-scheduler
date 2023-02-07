package com.edgar.contact.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.contact.models.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	

	Boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

	
}