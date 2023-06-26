package com.findhub.finhubbackend.entity.deliverableType;

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
@Table(name = "DeliverableType", uniqueConstraints = @UniqueConstraint(columnNames = "Name"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliverableType extends MyEntity {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Nationalized
    @Column(name = "Name", nullable = false)
    private String name;

    @Default
    @Column(name = "Status", nullable = false)
    private int status = DeliverableTypeStatus.ACTIVE.getValue();

    // @ManyToMany(mappedBy = "deliverableTypeSet")
    // private Set<Project> projectSet;
}
