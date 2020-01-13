package com.iot.app;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnextionAppApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(ConnextionAppApplication.class, args);
		System.out.println(">>>>"+LocalDateTime.now());
		
		
	}

}
