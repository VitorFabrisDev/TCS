package com.tcs_senac.ruralfacil;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.tcs_senac.ruralfacil.model.Enum.Sazonalidade;
import com.tcs_senac.ruralfacil.model.QAnuncioSazonalidade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@EnableWebMvc
@SpringBootApplication
@EnableJpaRepositories
public class RuralfacilApplication {

	public static void main(String[] args) {SpringApplication.run(RuralfacilApplication.class, args);



	}

}
