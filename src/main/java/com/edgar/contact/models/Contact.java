package com.edgar.contact.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Contact {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp // sets to current vm time stamp
	@Temporal(TemporalType.TIMESTAMP)// get sql time stamp 
	@JsonFormat(pattern ="MM-dd-yyyy")
	@Column(nullable = true)
	private Date date;
	
	@Column(nullable = true)
	private String avatarFileName;
	
	@Column(nullable = false)
	private String firstname; 
	
	@Column(nullable = true)
	private String lastname; 
	
	/*
	 * ^ - Start of the string
	 * [0-9] - Any digit between 0 to 9
	 * {10} - 10 times
	 * $ - End of the string
	 */
	
	@Column(nullable = false)
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone format,number should be a 10 digit number")
	private String number; // number of phone
	
	@Column(nullable = false)
    private String email;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private LocalDate birthday;
	
	
	public Contact () {
		
	}


	public Contact(Long id, Date date, String avatarFileName, String firstname, String lastname,
			@Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone format,number should be a 10 digit number") String number,
			String email, LocalDate birthday) {
		super();
		this.id = id;
		this.date = date;
		this.avatarFileName = avatarFileName;
		this.firstname = firstname;
		this.lastname = lastname;
		this.number = number;
		this.email = email;
		this.birthday = birthday;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getAvatarFileName() {
		return avatarFileName;
	}


	public void setAvatarFileName(String avatarFileName) {
		this.avatarFileName = avatarFileName;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDate getBirthday() {
		return birthday;
	}


	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}


	@Override
	public String toString() {
		return "Contact [id=" + id + ", date=" + date + ", avatarFileName=" + avatarFileName + ", firstname="
				+ firstname + ", lastname=" + lastname + ", number=" + number + ", email=" + email + ", birthday="
				+ birthday + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(avatarFileName, birthday, date, email, firstname, id, lastname, number);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(avatarFileName, other.avatarFileName) && Objects.equals(birthday, other.birthday)
				&& Objects.equals(date, other.date) && Objects.equals(email, other.email)
				&& Objects.equals(firstname, other.firstname) && Objects.equals(id, other.id)
				&& Objects.equals(lastname, other.lastname) && Objects.equals(number, other.number);
	}


	
	
	

}
