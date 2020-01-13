package com.iot.app.dto;

import java.time.LocalDateTime;
import com.iot.app.enums.SocialTypeEnum;
import com.iot.app.enums.SpaOwnerStatusEnum;
import com.iot.app.enums.StatusEnum;

public class DataResponseDto {
	
	private Long userId;
	
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

	public final Long getUserId() {
		return userId;
	}

	public final void setUserId(Long userId) {
		this.userId = userId;
	}

	public final String getFirstName() {
		return firstName;
	}

	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public final String getLastName() {
		return lastName;
	}

	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public final String getEmailAddress() {
		return emailAddress;
	}

	public final void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public final StatusEnum getStatus() {
		return status;
	}

	public final void setStatus(StatusEnum status) {
		this.status = status;
	}

	public final SocialTypeEnum getSocialType() {
		return socialType;
	}

	public final void setSocialType(SocialTypeEnum socialType) {
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

	public final String getCreatedBy() {
		return createdBy;
	}

	public final void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public final String getUpdatedBy() {
		return updatedBy;
	}

	public final void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public final LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public final void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public final LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public final void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
