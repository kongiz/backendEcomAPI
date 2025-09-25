package com.backend.ecom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.backend.ecom.services.UserService;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		    
        return http
            .csrf(csrf -> csrf.disable()) // for Postman/API testing
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                		"/api/auth/**",
                		"/api/users",
                		"/api/images/**"
                	).permitAll() // ✅ allow registration/login
                .anyRequest().authenticated() // others require auth
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
			.build();
    }
	
	@Bean
	public JwtAuthFilter jwtAuthFilter() {
		return new JwtAuthFilter();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}
}
