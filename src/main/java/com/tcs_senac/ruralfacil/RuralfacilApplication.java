package com.tcs_senac.ruralfacil;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableJpaRepositories
public class RuralfacilApplication {

	public static void main(String[] args) {SpringApplication.run(RuralfacilApplication.class, args);


	}

}
