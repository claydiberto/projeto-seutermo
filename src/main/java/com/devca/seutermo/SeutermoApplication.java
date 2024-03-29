package com.devca.seutermo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SeutermoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SeutermoApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
		
	}

}
