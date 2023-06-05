package com.findhub.finhubbackend.entity.project;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Project")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {
	@Id
	@Column(
			name= "Id",
			nullable = false
	)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Nationalized
	@Column(
			name= "Title",
			nullable = false
	)
	private String title;
	
	@Nationalized
	@Column(
			name= "Type",
			nullable = true
	)
	private String type;
	
	@Nationalized
	@Column(
			name= "Description",
			nullable = true
	)
	private String description;
	
	@Column(
			name= "Wage",
			nullable = true
	)
	private float wage;
	
	@Column(
			name= "ImageURL",
			nullable = true
	)
	private String imageURL;
	
	@Column(
			name= "DeliverDays",
			nullable = false
	)
	private int deliverDays;
	
	@Column(
			name= "PublisherDate",
			nullable = false
	)
	private Date publishDate;
	
	@Column(
			name= "Status",
			nullable = false
	)
	private int status;
	
	
	
	
	
	
}
