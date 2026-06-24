package com.service.shortr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShortrApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortrApplication.class, args);
	}

}