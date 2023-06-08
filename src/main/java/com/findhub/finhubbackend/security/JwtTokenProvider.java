package com.findhub.finhubbackend.security;

import java.sql.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();
		Date currentDate = new Date(System.currentTimeMillis());
		Date expireDate = new Date(currentDate.getTime() + JwtConstants.JWT_EXPIRATION);
		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(currentDate)
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, JwtConstants.JWT_SECRET)
				.compact();
		return token;
	}
	
	public String getUsernameFromJwt(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(JwtConstants.JWT_SECRET)
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser()
			.setSigningKey(JwtConstants.JWT_SECRET)
			.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			
		}throw new AuthenticationCredentialsNotFoundException("Jwt was expired or incorrect");
	}
}
