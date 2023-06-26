package com.findhub.finhubbackend.entity.publisher;

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
import com.findhub.finhubbackend.entity.member.MemberStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "Publisher", uniqueConstraints = @UniqueConstraint(columnNames = "Email"))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Publisher extends MyEntity {
	@Id
	@Column(name = "Id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "Email", nullable = false)
	private String email;

	@Nationalized
	@Column(name = "Name", nullable = true)
	private String name;

	@Column(name = "Phone", nullable = true)
	private String phone;

	@Column(name = "DOB", nullable = true)
	private Date dob;

	@Default
	@Column(name = "Balance", nullable = false)
	private float balance = 0;

	@Default
	@Column(name = "Status", nullable = false)
	private int status = MemberStatus.AVAILABLE.getValue();
}
