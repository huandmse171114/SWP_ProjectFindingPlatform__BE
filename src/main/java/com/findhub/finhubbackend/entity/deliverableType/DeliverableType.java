package com.findhub.finhubbackend.entity.deliverableType;

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
import com.findhub.finhubbackend.entity.projectDeliverable.ProjectDeliverable;

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
    name = "Output",
    uniqueConstraints = @UniqueConstraint(
        columnNames = "Name"
    )
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class DeliverableType extends MyEntity {
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
        name = "Status",
        nullable = false
    )
    private int status = DeliverableStatus.ACTIVE.getValue();

    @OneToMany(
        mappedBy = "deliverableType",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<ProjectDeliverable> projects;
}
