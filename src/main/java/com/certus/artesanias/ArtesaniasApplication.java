package com.certus.artesanias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class ArtesaniasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtesaniasApplication.class, args);
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	//System.out.println(encoder.encode("Admin123#"));
	}

}
