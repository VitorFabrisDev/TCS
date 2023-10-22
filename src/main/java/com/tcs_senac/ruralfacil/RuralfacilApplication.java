package com.tcs_senac.ruralfacil;

import com.tcs_senac.ruralfacil.teste.TesteController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class RuralfacilApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuralfacilApplication.class, args);

	}

}
