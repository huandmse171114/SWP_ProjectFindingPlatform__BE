package com.findhub.finhubbackend.entity.team;

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

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.findhub.finhubbackend.entity.entity.MyEntity;
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
@Table(name = "Team")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team extends MyEntity {
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
    private int status = TeamStatus.ACTIVE.getValue();

    @OneToMany(
		mappedBy = "team",
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY
	)
	@JsonManagedReference
	private List<TeamMember> members;
}
