package com.iot.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignUpDto {

	@Size(min = 3, max = 30,message = "firstName must contain atleast 3 characters")
	private String firstName;

	@Size(min = 3, max = 30,message = "lastName must contain atleast 3 characters")
	private String lastName;

	@NotEmpty(message = "email must be provided")
	@Email(message = "provided valid email address")
//	@Pattern(regexp = "")
	private String emailAddress;

	@NotEmpty(message = "password must not be empty")
//	@Pattern(regexp = "")
	private String password;
	
	@NotEmpty(message = "DeviceToken required")
	private String mobileDeviceToken;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileDeviceToken() {
		return mobileDeviceToken;
	}

	public void setMobileDeviceToken(String mobileDeviceToken) {
		this.mobileDeviceToken = mobileDeviceToken;
	}
	
	
	
}
