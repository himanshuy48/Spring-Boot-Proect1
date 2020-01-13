package com.iot.app.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class LoginDto implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	@NotEmpty(message = "email must not be empty")
	@Email(message = "email should be a valid email")
	private String emailAddress;

	@NotEmpty(message = "password must not be empty")
	private String password;
	
	@NotEmpty(message = "mobileDeviceToken must not be empty")
	private String mobileDeviceToken;

	// need default constructor for JSON Parsing
	public LoginDto() {

	}

	private LoginDto(
			@NotEmpty(message = "email must not be empty") @Email(message = "email should be a valid email") String emailAddress,
			@NotEmpty(message = "password must not be empty") String password, String mobileDeviceToken) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
		this.mobileDeviceToken = mobileDeviceToken;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return this.password;
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