package com.example.Iprwcbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@RestController
@EnableJpaRepositories(basePackages = {"com.example.Iprwcbackend.module.user", "com.example.Iprwcbackend.module.product", "com.example.Iprwcbackend.module.category", "com.example.Iprwcbackend.module.order"})
public class IprwcBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IprwcBackendApplication.class, args);
	}

}
