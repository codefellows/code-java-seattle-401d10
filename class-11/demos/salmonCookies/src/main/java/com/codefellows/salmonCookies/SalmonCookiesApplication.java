package com.codefellows.salmonCookies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Spring does all its work behind the scenes based on ANNOTATIONS

//This app runs because of @SpringBootApplication

@SpringBootApplication
public class SalmonCookiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalmonCookiesApplication.class, args);
	}

}
