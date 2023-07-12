package com.findhub.finhubbackend.entity.teamProject;

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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.findhub.finhubbackend.entity.entity.MyEntity;
import com.findhub.finhubbackend.entity.entity.Status;
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
@Table(
    name = "Team_Project",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {
            "TeamId",
            "ProjectId"
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
public class TeamProject extends MyEntity {
    @Id
    @Column(
        name = "Id",
        nullable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

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

    @Default
    @Column(
        name = "StartDate",
        nullable = false
    )
    private Date startDate = new Date(System.currentTimeMillis());

    @Column(
        name = "EndDate",
        nullable = true
    )
    private Date endDate;

    @Default
    private float wage = 0;

    @Default
    @Column(
        name = "Status",
        nullable = false
    )
    protected int status = Status.ACTIVE.getValue();
}
