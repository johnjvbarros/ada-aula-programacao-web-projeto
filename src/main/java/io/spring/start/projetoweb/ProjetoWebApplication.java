package io.spring.start.projetoweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class ProjetoWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoWebApplication.class, args);
	}

}
