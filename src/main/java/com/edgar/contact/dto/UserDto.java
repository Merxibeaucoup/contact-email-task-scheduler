package com.edgar.contact.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto {

	@NotBlank(message = "Name cannot be empty")
	@Size(max = 20, message = "maximum length 20 characters")
	@Size(min = 5, message = "minimum length 5 characters")
	private String name;

	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Invalid Email")
	private String email;

	@NotBlank(message = "password cannot be empty")
	@Size(min = 8, message = "Password too Short")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
