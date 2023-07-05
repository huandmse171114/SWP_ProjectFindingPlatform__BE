package com.findhub.finhubbackend.entity.application;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "Application")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Application extends MyEntity {

	@Id
	@Column(
		name = "Id",
		nullable = false
	)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(
		name = "ProjectId",
		nullable = false
	)
	private int projectId;

	@Column(
		name = "TeamId",
		nullable = false
	)
	private int teamId;

	@Default
	@Column(
		name = "CreateDate",
		nullable = false
	)
	private Date createDate = new Date(System.currentTimeMillis());

	@Default
	@Column(
		name = "Status",
		nullable = false
	)
	private int status = ApplicationStatus.PENDING.getValue();
}
