package com.iot.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iot.app.entity.BlackListedTokens;

public interface BlackListTokenDao extends JpaRepository<BlackListedTokens, Long> {

	Boolean existsByJwtToken(String token);

}
