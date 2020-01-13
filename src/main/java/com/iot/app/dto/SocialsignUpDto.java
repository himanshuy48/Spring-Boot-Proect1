package com.iot.app.dto;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.iot.app.enums.SocialTypeEnum;

public class SocialsignUpDto {

	@Size(min = 3, max = 30,message = "firstName must contain atleast 3 characters")
	private String firstName;

	@Size(min = 3, max = 30,message = "lastName must contain atleast 3 characters")
	private String lastName;

	@NotEmpty(message = "email must be provided")
	@Email(message = "provided valid email address")
//	@Pattern(regexp = "")
	private String emailAddress;

	@NotEmpty(message = "DeviceToken required")
	private String mobileDeviceToken;
	
	@NotEmpty(message = "social id required for this signup ")
	private String socialId;
	
	@Enumerated
	private SocialTypeEnum socialType;

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

	public String getMobileDeviceToken() {
		return mobileDeviceToken;
	}

	public void setMobileDeviceToken(String mobileDeviceToken) {
		this.mobileDeviceToken = mobileDeviceToken;
	}

	public String getSocialId() {
		return socialId;
	}

	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}

	public SocialTypeEnum getSocialType() {
		return socialType;
	}

	public void setSocialType(SocialTypeEnum socialType) {
		this.socialType = socialType;
	}
	
}
