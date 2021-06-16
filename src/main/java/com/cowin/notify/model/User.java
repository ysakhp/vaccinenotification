package com.cowin.notify.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user")
public class User {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "email")
	String email;

	@Column(name = "pincode")
	int pincode;
	
	boolean emailSent = false;
	
	int emailCount = 0;
	
	String emailSendDate = "";

	public User() {

	}

	public User(int id, String email, int pincode) {
		super();
		this.id = id;
		this.email = email;
		this.pincode = pincode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public boolean isEmailSent() {
		return emailSent;
	}

	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}

	public int getEmailCount() {
		return emailCount;
	}

	public void setEmailCount(int emailCount) {
		this.emailCount = emailCount;
	}

	public String getEmailSendDate() {
		return emailSendDate;
	}

	public void setEmailSendDate(String emailSendDate) {
		this.emailSendDate = emailSendDate;
	}

	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", pincode=" + pincode + ", emailSent=" + emailSent
				+ ", emailCount=" + emailCount + ", emailSendDate=" + emailSendDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + emailCount;
		result = prime * result + ((emailSendDate == null) ? 0 : emailSendDate.hashCode());
		result = prime * result + (emailSent ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + pincode;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailCount != other.emailCount)
			return false;
		if (emailSendDate == null) {
			if (other.emailSendDate != null)
				return false;
		} else if (!emailSendDate.equals(other.emailSendDate))
			return false;
		if (emailSent != other.emailSent)
			return false;
		if (id != other.id)
			return false;
		if (pincode != other.pincode)
			return false;
		return true;
	}

	
	
}
