package com.findhub.finhubbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.findhub.finhubbackend.security.CustomUserDetailService;

@EnableWebSecurity
public class ApplicationSecurityConfig {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
            .authorizeHttpRequests()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/api/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
        return http.build();
    }
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails annaSmithUser = User.builder()
				.username("annasmith")
				.password("password")
				.roles("USER")
				.build();
		
		UserDetails adminUser = User.builder()
				.username("huandmse")
				.password("password")
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(
				annaSmithUser,
				adminUser
		);		
	}
	
	@Bean
	public AuthenticationManager authenticationManager
		(AuthenticationConfiguration authenticationConfiguration)
		throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
