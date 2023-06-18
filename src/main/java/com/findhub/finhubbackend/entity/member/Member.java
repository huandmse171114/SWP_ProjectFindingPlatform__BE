package com.findhub.finhubbackend.entity.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Nationalized;
import org.springframework.data.annotation.Transient;

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
@Table(name = "Member", uniqueConstraints = @UniqueConstraint(columnNames = "Email"))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends MyEntity {
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

	@Column(name = "Balance", nullable = false)
	@Default
	private float balance = 0;

	@Column(name = "Status", nullable = false)
	@Default
	private int status = MemberStatus.AVAILABLE.getValue();

	@Column(name = "MajorId", nullable = true)
	private int majorId;

	// @Transient
	// public String getMajorName() {
	// return null;
	// }
}
