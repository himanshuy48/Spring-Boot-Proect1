package com.iot.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.iot.app.enums.SocialTypeEnum;
import com.iot.app.enums.SpaOwnerStatusEnum;
import com.iot.app.enums.StatusEnum;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 3, max = 30)
	private String firstName;

	@Size(min = 3, max = 30)
	private String lastName;

	@NotEmpty
//	@Pattern(regexp = "")
	private String emailAddress;

	@NotEmpty
//	@Pattern(regexp = "")
	private String password;

	@Enumerated(EnumType.STRING)
	private StatusEnum status = StatusEnum.EMAIL_UN_VERIFIED;

	private String socialId;

	@Enumerated(EnumType.STRING)
	private SocialTypeEnum socialType=SocialTypeEnum.NONE;

	private String tempVerificationCode;

	@Enumerated(EnumType.STRING)
	private SpaOwnerStatusEnum spaOwnerStatus;

	private String mobileDeviceToken;

	private String createdBy;

	private String updatedBy;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
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

	public String getTempVerificationCode() {
		return tempVerificationCode;
	}

	public void setTempVerificationCode(String tempVerificationCode) {
		this.tempVerificationCode = tempVerificationCode;
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
