package com.findhub.finhubbackend.entity.skill;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Nationalized;

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
@Table(name = "Skill", uniqueConstraints = @UniqueConstraint(columnNames = "Name"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill extends MyEntity {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Nationalized
    @Column(name = "Name",nullable = false)
    private String name;

    @Default
    @Column(name = "Status", nullable = false)
    private int status = SkillStatus.ACTIVE.getValue();

    // @ManyToMany(mappedBy = "skillSet")
    // private Set<Project> projectSet;
}
