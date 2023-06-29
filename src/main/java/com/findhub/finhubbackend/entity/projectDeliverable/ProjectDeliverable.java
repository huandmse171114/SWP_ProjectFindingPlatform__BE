package com.findhub.finhubbackend.entity.projectDeliverable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "ProjectDeliverable")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDeliverable extends MyEntity {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ProjectId", nullable = false)
    private int projectId;

    @Nationalized
    @Column(name = "Name", nullable = false)
    private String name;

    @Nationalized
    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "DeliverableTypeId", nullable = false)
    private int deliverableTypeId;

    @Default
    @Column(name = "Status", nullable = false)
    private int status = ProjectDeliverableStatus.ACTIVE.getValue();

}
