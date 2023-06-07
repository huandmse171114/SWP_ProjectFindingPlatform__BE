package com.findhub.finhubbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRegisterModel {
	private String email;
	private String password;
	private int role;
}
