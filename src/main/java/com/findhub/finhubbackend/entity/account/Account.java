package com.findhub.finhubbackend.entity.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.findhub.finhubbackend.entity.entity.MyEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(
	name = "Account",
	uniqueConstraints = @UniqueConstraint(
		columnNames = "Email"
	)
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends MyEntity {

	@Id
	@Column(
		name = "Id",
		nullable = false
	)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(
		name = "Email",
		nullable = false
	)
	private String email;

	@Column(
		name = "Password",
		nullable = false
	)
	private String password;

	@Column(
		name = "Status",
		nullable = false
	)
	@Default
	private int status = AccountStatus.ACTIVE.getValue();

	@Column(
		name = "Role",
		nullable = false
	)
	@Default
	private int role = AccountRole.MEMBER.getValue();

}
