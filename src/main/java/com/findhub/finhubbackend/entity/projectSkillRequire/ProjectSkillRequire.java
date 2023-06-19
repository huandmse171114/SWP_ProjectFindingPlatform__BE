package com.findhub.finhubbackend.entity.projectSkillRequire;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "ProjectSkillRequire", uniqueConstraints = @UniqueConstraint(columnNames = { "SkillId", "ProjectId" }))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectSkillRequire {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "SkillId", nullable = false)
    private int skillId;

    @Column(name = "ProjectId", nullable = false)
    private int projectId;

    @Column(name = "Level", nullable = false)
    private int level;
}
