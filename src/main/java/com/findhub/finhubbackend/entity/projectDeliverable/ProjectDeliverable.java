package com.findhub.finhubbackend.entity.projectDeliverable;

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
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.findhub.finhubbackend.entity.deliverableType.DeliverableType;
import com.findhub.finhubbackend.entity.entity.MyEntity;
import com.findhub.finhubbackend.entity.project.Project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "Project_Output")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProjectDeliverable extends MyEntity {
    @Id
    @Column(
        name = "Id",
        nullable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Nationalized
    @Column(
        name = "Value",
        nullable = true
    )
    private String value;

    @Nationalized
    @Column(
        name = "Description",
        nullable = true
    )
    private String description;

    // @Column(
    // name = "ProjectId",
    // nullable = false
    // )
    // private int projectId;

    // @Column(
    // name = "DeliverableTypeId",
    // nullable = false
    // )
    // private int outputId;

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
    @JoinColumn(name = "DeliverableTypeId")
    private DeliverableType deliverableType;

    @Default
    @Column(
        name = "Status",
        nullable = false
    )
    private int status = ProjectDeliverableStatus.ACTIVE.getValue();

}
