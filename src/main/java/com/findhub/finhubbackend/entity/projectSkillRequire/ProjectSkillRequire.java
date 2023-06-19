package com.findhub.finhubbackend.entity.projectSkillRequire;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import javax.persistence.UniqueConstraint;

import com.findhub.finhubbackend.embeddable.SkillLevel;
import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.entity.skill.Skill;

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

    // @EmbeddedId
    // SkillLevel skillLevel;

    @ManyToOne
    // @MapsId("projectId")
    @JoinColumn(name = "ProjectId")
    private Project project;

    @ManyToOne
    // @MapsId("skillId")
    @JoinColumn(name = "SkillId")
    private Skill skill;

    @Column(name = "Level", nullable = false)
    private int level;
}
