package com.findhub.finhubbackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.findhub.finhubbackend.entity.account.AccountRole;
import com.findhub.finhubbackend.security.JwtAuthEntryPoint;
import com.findhub.finhubbackend.security.JwtAuthFilter;

@EnableWebSecurity
public class ApplicationSecurityConfig {
	
	@Autowired
	private JwtAuthEntryPoint jwtAuthEntryPoint;
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
        	.exceptionHandling()
//        	.authenticationEntryPoint(jwtAuthEntryPoint)
        	.and()
        	.sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        	.and()
            .authorizeHttpRequests()
//            .antMatchers(
//            		"/", 
//            		"/api/auth/**", 
//            		"/swagger-ui/**",
//            		"/swagger-resources/**",
//            		"/v2/api-docs"
//            ).permitAll()
//            .antMatchers(
//            		HttpMethod.GET,
//            		"/api/projects/**", 
//            		"/api/members/**"
//            ).permitAll()
//            .antMatchers(
//            		HttpMethod.PUT, 
//            		"/api/projects/**"
//            ).hasAnyAuthority(
//            		AccountRole.PUBLISHER.toString(),
//            		AccountRole.ADMIN.toString()
//            )
//            .antMatchers(
//            		"/api/accounts/**", 
//            		"/api/skills/**", 
//            		"/api/categories/**"
//            ).hasAuthority("ADMIN")
//            .anyRequest().authenticated()
            .anyRequest().permitAll()
            .and()
            .httpBasic();
        
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
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
