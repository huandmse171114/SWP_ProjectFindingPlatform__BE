package com.findhub.finhubbackend.entity.major;

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
@Table(name = "Major", uniqueConstraints = @UniqueConstraint(columnNames = { "Code", "Name" }))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Major extends MyEntity {

	@Id
	@Column(name = "Id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Code", nullable = false)
	private String code;

	@Column(name = "Name", nullable = false)
	private String name;

	@Column(name = "Status", nullable = false)
	@Default
	private int status = MajorStatus.ACTIVE.getValue();
}
