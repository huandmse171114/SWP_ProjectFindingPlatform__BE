package com.findhub.finhubbackend.entity.project;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.findhub.finhubbackend.entity.application.Application;
import com.findhub.finhubbackend.entity.entity.MyEntity;
import com.findhub.finhubbackend.entity.projectCategory.ProjectCategory;
import com.findhub.finhubbackend.entity.projectDeliverable.ProjectDeliverable;
import com.findhub.finhubbackend.entity.projectSkill.ProjectSkill;
import com.findhub.finhubbackend.entity.teamProject.TeamProject;

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
	name = "Project",
	uniqueConstraints = @UniqueConstraint(
		columnNames = {
			"Name",
			"ImageURL"
		}
	)
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(
	generator = ObjectIdGenerators.PropertyGenerator.class,
	property = "id"
)
public class Project extends MyEntity {
	@Id
	@Column(
		name = "Id",
		nullable = false
	)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Nationalized
	@Column(
		name = "Name",
		nullable = false
	)
	private String name;

	@Column(
		name = "PublisherId",
		nullable = false
	)
	private int publisherId;

	// @OneToOne
	// private Publisher publisher;

	@Nationalized
	@Column(
		name = "Description",
		nullable = true
	)
	private String description;

	@Column(
		name = "Wage",
		nullable = true
	)
	private float wage;

	@Column(
		name = "ImageURL",
		nullable = true
	)
	private String imageURL;

	@Column(
		name = "DeliverDays",
		nullable = false
	)
	private int deliverDays;

	@Default
	@Column(
		name = "PublishDate",
		nullable = true
	)
	private Date publishDate = new Date(System.currentTimeMillis());

	@Column(
		name = "DueDate",
		nullable = false
	)
	private Date dueDate;

	@Default
	@Column(
		name = "Status",
		nullable = false
	)
	private int status = ProjectStatus.ACTIVE.getValue();

	@OneToMany(
		mappedBy = "project",
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY
	)
	@JsonManagedReference
	private List<ProjectSkill> skills;

	@OneToMany(
		mappedBy = "project",
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY
	)
	@JsonManagedReference
	private List<ProjectCategory> categories;

	@OneToMany(
		mappedBy = "project",
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY
	)
	@JsonManagedReference
	private List<ProjectDeliverable> deliverables;

	@OneToMany(
		mappedBy = "project",
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY
	)
	@JsonManagedReference
	private List<TeamProject> teams;

	@OneToMany(
		mappedBy = "project",
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY
	)
	@JsonManagedReference
	private List<Application> applications;
}
