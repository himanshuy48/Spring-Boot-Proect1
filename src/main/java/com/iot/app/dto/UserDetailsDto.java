package com.iot.app.dto;

import java.time.LocalDateTime;

import com.iot.app.enums.SocialTypeEnum;
import com.iot.app.enums.SpaOwnerStatusEnum;
import com.iot.app.enums.StatusEnum;

public class UserDetailsDto {

	private Long id;

	private String firstName;

	private String lastName;

	private String emailAddress;

	private StatusEnum status;

	private SocialTypeEnum socialType;

	private SpaOwnerStatusEnum spaOwnerStatus;
	
	private String mobileDeviceToken;

	private String createdBy;

	private String updatedBy;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public UserDetailsDto() {
		super();
	}

	public UserDetailsDto(Long id, String firstName, String emailAddress) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public SocialTypeEnum getSocialType() {
		return socialType;
	}

	public void setSocialType(SocialTypeEnum socialType) {
		this.socialType = socialType;
	}

	public SpaOwnerStatusEnum getSpaOwnerStatus() {
		return spaOwnerStatus;
	}

	public void setSpaOwnerStatus(SpaOwnerStatusEnum spaOwnerStatus) {
		this.spaOwnerStatus = spaOwnerStatus;
	}

	public String getMobileDeviceToken() {
		return mobileDeviceToken;
	}

	public void setMobileDeviceToken(String mobileDeviceToken) {
		this.mobileDeviceToken = mobileDeviceToken;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}