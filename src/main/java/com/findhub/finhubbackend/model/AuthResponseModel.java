package com.findhub.finhubbackend.model;

import lombok.Data;

@Data
public class AuthResponseModel {
	private String accessToken;
	private String tokenType = "Bearer ";
	private String role;
	private int id;
	
	public AuthResponseModel(String accessToken, String role, int id) {
		this.accessToken = accessToken;
		this.role = role;
		this.id = id;
	}
	
}
