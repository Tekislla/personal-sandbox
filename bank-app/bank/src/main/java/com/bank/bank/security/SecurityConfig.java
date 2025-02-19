package com.bank.bank.security;

import javax.ws.rs.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
		jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
		
		http.authorizeRequests(auth->auth.antMatchers(HttpMethod.GET, "/actuator/**", "/api-docs/**", "/api-docs.yaml**").permitAll().anyRequest().authenticated()).oauth2ResourceServer(oauth->oauth.jwt().jwtAuthenticationConverter(jwtConverter));
	
	}
}
