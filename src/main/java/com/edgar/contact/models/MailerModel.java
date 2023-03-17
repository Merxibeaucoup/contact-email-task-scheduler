package com.edgar.contact.models;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.edgar.contact.enums.StatusEmail;

@Entity
public class MailerModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String emailFrom;
	@Column(nullable = false)
	private String emailTo;
	@Column(nullable = true)
	private String subject;
	@Column(columnDefinition = "TEXT")
	private String message;
	
	private LocalDateTime SentDateTime;
	
	
	private StatusEmail status;
	
	public MailerModel() {
		
	}

	public MailerModel(Long id, String emailFrom, String emailTo, String subject, String message,
			LocalDateTime sentDateTime, StatusEmail status) {
		super();
		this.id = id;
		this.emailFrom = emailFrom;
		this.emailTo = emailTo;
		this.subject = subject;
		this.message = message;
		SentDateTime = sentDateTime;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getSentDateTime() {
		return SentDateTime;
	}

	public void setSentDateTime(LocalDateTime localDateTime) {
		SentDateTime = localDateTime;
	}

	public StatusEmail getStatus() {
		return status;
	}

	public void setStatus(StatusEmail status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MailerModel [Id=" + id + ", emailFrom=" + emailFrom + ", emailTo=" + emailTo + ", subject=" + subject
				+ ", message=" + message + ", SentDateTime=" + SentDateTime + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, SentDateTime, emailFrom, emailTo, message, status, subject);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MailerModel other = (MailerModel) obj;
		return Objects.equals(id, other.id) && Objects.equals(SentDateTime, other.SentDateTime)
				&& Objects.equals(emailFrom, other.emailFrom) && Objects.equals(emailTo, other.emailTo)
				&& Objects.equals(message, other.message) && status == other.status
				&& Objects.equals(subject, other.subject);
	}
	
	

}
