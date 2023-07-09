package com.findhub.finhubbackend.entity.member;

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
import com.findhub.finhubbackend.entity.entity.MyEntity;
import com.findhub.finhubbackend.entity.memberSkill.MemberSkill;
import com.findhub.finhubbackend.entity.teamMember.TeamMember;

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
	name = "Member",
	uniqueConstraints = @UniqueConstraint(
		columnNames = "Email"
	)
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class Member extends MyEntity {
	@Id
	@Column(
		name = "Id",
		nullable = false
	)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(
		name = "Email",
		nullable = false
	)
	private String email;

	@Nationalized
	@Column(
		name = "Description",
		nullable = true
	)
	private String description;

	@Nationalized
	@Column(
		name = "Name",
		nullable = true
	)
	private String name;

	@Column(
		name = "Phone",
		nullable = true
	)
	private String phone;

	// @Column(
		// name = "Level",
		// nullable = true
	// )
	// private int level;

	@Column(
		name = "DOB",
		nullable = true
	)
	private Date dob;

	@Default
	@Column(
		name = "Balance",
		nullable = false
	)
	private float balance = 0;

	@Default
	@Column(
		name = "Status",
		nullable = false
	)
	private int status = MemberStatus.AVAILABLE.getValue();

	@Column(
		name = "MajorId",
		nullable = true
	)
	private int majorId;

	@OneToMany(
        mappedBy = "member",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<TeamMember> teams;

	@OneToMany(
        mappedBy = "skill",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<MemberSkill> skills;
}
