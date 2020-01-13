package com.iot.app.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BlackListedTokens {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String jwtToken;

	private LocalDateTime blackListedAt;

	private Date tokenExpireAt;

	public BlackListedTokens(String jwtToken, LocalDateTime blackListedAt, Date tokenExpireAt) {
		super();
		this.jwtToken = jwtToken;
		this.blackListedAt = blackListedAt;
		this.tokenExpireAt = tokenExpireAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public LocalDateTime getBlackListedAt() {
		return blackListedAt;
	}

	public void setBlackListedAt(LocalDateTime blackListedAt) {
		this.blackListedAt = blackListedAt;
	}

	public Date getTokenExpireAt() {
		return tokenExpireAt;
	}

	public void setTokenExpireAt(Date tokenExpireAt) {
		this.tokenExpireAt = tokenExpireAt;
	}

	

}
