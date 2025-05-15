package com.projeto.ads.security;

import org.hibernate.cache.internal.DisabledCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig {

@Bean
	public static PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();	
}
// Método para configurar o AuthenticationManager (gerenciador de autenticação)
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
throws Exception { //gerencia autenticação
	return configuration.getAuthenticationManager();
}//fim metodo
// Método para configurar o filtro de segurança
@Bean

public SecurityFilterChain securityFilterchain(HttpSecurity http) throws Exception {
	http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(authorize ->
			authorize
			.requestMatchers("/css/**", "/js/**").permitAll()
			.requestMatchers("/usuario/inserir").permitAll()
			.anyRequest().authenticated() //todas as requisições precisam de authenticação
		)
		.formLogin(form ->
			form
			
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.successHandler((request, response, authentication)->response.sendRedirect("/dashboard"))
				.permitAll() //permite acesso publico ao nosso formulario de login
		);
	return http.build();
}//fim metodo securityFilterChain
	
}//fim class
