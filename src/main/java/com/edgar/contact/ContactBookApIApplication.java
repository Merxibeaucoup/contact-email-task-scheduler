package com.edgar.contact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ContactBookApIApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactBookApIApplication.class, args);
	}

}
