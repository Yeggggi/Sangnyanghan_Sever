package com.example.hellohamsterdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HellohamsterdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellohamsterdemoApplication.class, args);
	}

}
