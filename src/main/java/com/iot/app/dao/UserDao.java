package com.iot.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iot.app.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

	boolean existsByEmailAddress(String emailAddress);

	Optional<User> findByEmailAddress(String username);

}
