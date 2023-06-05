package com.findhub.finhubbackend.entity.major;

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
@Table(
		name = "Major",
		uniqueConstraints = @UniqueConstraint(columnNames = {"Code", "Name"})
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Major {
	
	@Id
	@Column(
			name= "Id",
			nullable = false
	)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(
			name= "Code",
			nullable = false
	)
	private String code;
	
	@Column(
			name= "Name",
			nullable = false
	)
	private String name;
	
	@Column(
			name= "Status",
			nullable = false
	)
	private int status;
}
