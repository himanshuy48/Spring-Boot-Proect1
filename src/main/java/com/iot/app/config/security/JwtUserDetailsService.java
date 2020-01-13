package com.iot.app.config.security;

import java.util.ArrayList;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.iot.app.dao.UserDao;
import com.iot.app.dto.UserDetailsDto;
import com.iot.app.entity.User;

@Service
public class JwtUserDetailsService implements UserDetailsService,JwtUserDetailsServiceInf{

	@Autowired
	private UserDao userDao;
	
	ModelMapper modelMapper=new ModelMapper();

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		Optional<User> user = userDao.findByEmailAddress(emailId);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + user.get().getEmailAddress());
		}
		return new org.springframework.security.core.userdetails.User(user.get().getEmailAddress(), user.get().getPassword(),
				new ArrayList<>());}

	@Override
	public UserDetailsDto loadUserByEmail(String emailId) throws UsernameNotFoundException {
		Optional<User> user = userDao.findByEmailAddress(emailId);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + user.get().getEmailAddress());
		}
		UserDetailsDto loginResponse=new UserDetailsDto();
		modelMapper.map(user.get(), loginResponse);
		return loginResponse;
	}

	

	
}