package com.findhub.finhubbackend.entity.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Nationalized;
import org.springframework.boot.context.properties.bind.DefaultValue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(
		name = "Member",
		uniqueConstraints = @UniqueConstraint(columnNames = "Email")
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
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
	
	@Nationalized
	@Column(
			name= "Fullname",
			nullable = true
	)
	private String fullName;
	
	@Column(
			name= "Phone",
			nullable = true
	)
	private String phone;
	
	@Column(
			name= "Balance",
			nullable = false
	)
	@Builder.Default
	private float balance = 0;
	
	@Column(
			name= "Status",
			nullable = false
	)
	private int status;
	
	@Column(
			name= "MajorCode",
			nullable = true
	)
	private String majorCode;
}
