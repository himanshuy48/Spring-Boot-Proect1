package com.iot.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Spa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 3, max = 30)
	private String spaName;
	
	private String spaLocation;

	@NotEmpty
	private String spaSerialNumber;

	@NotEmpty
	private String hnaNumber;

	@NotEmpty
	private String snaNumber;

	private String status;

	private String fk_user_id;

	private LocalDateTime installationDate=LocalDateTime.now();

	private String address;

	private String state;

	private String country;

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

	public String getSpaName() {
		return spaName;
	}

	public void setSpaName(String spaName) {
		this.spaName = spaName;
	}
	
	public String getSpaLocation() {
		return spaLocation;
	}

	public void setSpaLocation(String spaLocation) {
		this.spaLocation = spaLocation;
	}

	public String getSpaSerialNumber() {
		return spaSerialNumber;
	}

	public void setSpaSerialNumber(String spaSerialNumber) {
		this.spaSerialNumber = spaSerialNumber;
	}

	public String getHnaNumber() {
		return hnaNumber;
	}

	public void setHnaNumber(String hnaNumber) {
		this.hnaNumber = hnaNumber;
	}

	public String getSnaNumber() {
		return snaNumber;
	}

	public void setSnaNumber(String snaNumber) {
		this.snaNumber = snaNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFk_user_id() {
		return fk_user_id;
	}

	public void setFk_user_id(String fk_user_id) {
		this.fk_user_id = fk_user_id;
	}

	public LocalDateTime getInstallationDate() {
		return installationDate;
	}

	public void setInstallationDate(LocalDateTime installationDate) {
		this.installationDate = installationDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
