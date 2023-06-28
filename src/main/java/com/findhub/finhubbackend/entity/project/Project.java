package com.findhub.finhubbackend.entity.project;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Nationalized;

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
@Table(name = "Project", uniqueConstraints = @UniqueConstraint(columnNames = { "Name", "ImageURL" }))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project extends MyEntity {
	@Id
	@Column(name = "Id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Nationalized
	@Column(name = "Name", nullable = false)
	private String name;

	@Column(name = "PublisherId", nullable = false)
	private int publisherId;

	// @Nationalized
	// @Column(name = "DeliverableType", nullable = true)
	// private String DeliverableTypeId;

	// @JoinTable(name = "ProjectDeliverable", joinColumns = @JoinColumn(name =
	// "ProjectId"), inverseJoinColumns = @JoinColumn(name = "DeliverableTypeId"))
	// private Set<DeliverableType> deliverableTypeSet;

	@Nationalized
	@Column(name = "Description", nullable = true)
	private String description;

	@Column(name = "Wage", nullable = true)
	private float wage;

	@Column(name = "ImageURL", nullable = true)
	private String imageURL;

	@Column(name = "DeliverDays", nullable = false)
	private int deliverDays;

	@Column(name = "PublishDate", nullable = false)
	private Date publishDate;

	@Column(name = "DueDate", nullable = false)
	private Date dueDate;

	@Default
	@Column(name = "Status", nullable = false)
	private int status = ProjectStatus.ACTIVE.getValue();

	// @OneToMany(mappedBy = "project")
	// private Set<ProjectSkillRequire> projectSkillRequires;

	// @ManyToMany
	// @JoinTable(name = "ProjectSkillRequire", joinColumns = @JoinColumn(name =
	// "ProjectId"), inverseJoinColumns = @JoinColumn(name = "SkillId"))
	// private Set<Skill> skillSet;

	// @ManyToMany
	// @JoinTable(name = "ProjectCategoryDetail", joinColumns = @JoinColumn(name =
	// "ProjectId"), inverseJoinColumns = @JoinColumn(name = "CategoryId"))
	// private Set<Category> categorySet;

}
