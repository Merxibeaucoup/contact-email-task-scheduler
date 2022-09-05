package com.edgar.contact.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgar.contact.models.MailerModel;

@Repository
public interface MailerModelRepository extends JpaRepository<MailerModel, UUID> {

}
