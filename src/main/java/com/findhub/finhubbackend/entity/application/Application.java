package com.findhub.finhubbackend.entity.application;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.findhub.finhubbackend.entity.entity.MyEntity;
import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.team.Team;

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

	@ManyToOne(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
    )
    @JsonBackReference
    @JoinColumn(name = "ProjectId")
    private Project project;

	@ManyToOne(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
    )
    @JsonBackReference
    @JoinColumn(name = "TeamId")
    private Team team;

	@Nationalized
	@Column(
		name = "Message",
		nullable = true
	)
	private String message;

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
