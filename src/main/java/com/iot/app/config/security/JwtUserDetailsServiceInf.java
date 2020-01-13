package com.iot.app.config.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.iot.app.dto.UserDetailsDto;

public interface JwtUserDetailsServiceInf {

	UserDetailsDto loadUserByEmail(String emailId) throws UsernameNotFoundException;

}
