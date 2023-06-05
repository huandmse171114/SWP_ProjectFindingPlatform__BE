package com.findhub.finhubbackend.entity.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(
		name = "Account",
		uniqueConstraints = @UniqueConstraint(columnNames = "Email")
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
	
	@Id
	@Column(
			name= "Id",
			nullable = false
	)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(
			name= "Email",
			nullable = false
	)
	private String email;
	
	@Column(
			name= "Password",
			nullable = false
	)
	private String password;
	
	@Column(
			name= "Status",
			nullable = false
	)
	private int status;
	
	@Column(
			name= "Role",
			nullable = false
	)
	private int role;
	
}
