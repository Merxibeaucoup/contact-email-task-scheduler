package com.edgar.contact.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class Phone {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(nullable = false)
	private String label; // type of phone... ( mobile, work , home , school, fax , pager , etc)
	
	/*
	 * ^ - Start of the string
	 * [0-9] - Any digit between 0 to 9
	 * {10} - 10 times
	 * $ - End of the string
	 */
	
	@Column(nullable = false)
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone format,number should be a 10 digit number")
	private String number; // number of phone
	
	
	public Phone() {
		
	}


	public Phone(Long id, String label, String number) {
		super();
		this.id = id;
		this.label = label;
		this.number = number;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	@Override
	public String toString() {
		return "Phone [id=" + id + ", label=" + label + ", number=" + number + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, label, number);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phone other = (Phone) obj;
		return Objects.equals(id, other.id) && Objects.equals(label, other.label)
				&& Objects.equals(number, other.number);
	}
	
	
	
	

}
