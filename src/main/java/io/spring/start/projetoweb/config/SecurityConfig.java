package io.spring.start.projetoweb.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import io.spring.start.projetoweb.service.LoginUserService;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	
		return http
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers(HttpMethod.POST,"/usuarios","/usuarios/auth")
					.permitAll()
				.requestMatchers(PathRequest.toH2Console())
					.permitAll()
				.anyRequest().authenticated()
				.and()
				.headers().frameOptions().disable()
				.and()
				.userDetailsService(new LoginUserService())
				.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager(
			AuthenticationConfiguration configurer) {
		try {
			return configurer.getAuthenticationManager();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
