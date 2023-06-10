package com.findhub.finhubbackend.entity.application;

import java.sql.Date;

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
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Application")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Application extends MyEntity{

	@Id
	@Column(name = "Id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ProjectId", nullable = false)
	private int projectId;

	@Column(name = "TeamId", nullable = false)
	private int teamId;

	@Column(name = "CreateAt", nullable = false)
	private Date createAt;

	@Column(name = "Status", nullable = false)
	private int status;
}
