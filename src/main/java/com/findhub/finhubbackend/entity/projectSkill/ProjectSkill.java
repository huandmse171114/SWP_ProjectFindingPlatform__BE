package com.findhub.finhubbackend.entity.projectSkill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.findhub.finhubbackend.entity.entity.MyEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(
    name = "Project_Skill",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {
            "SkillId",
            "ProjectId"
        }
    )
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectSkill extends MyEntity {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // @ManyToOne
    // @JoinColumn(name = "ProjectId")
    // private Project project;

    // @ManyToOne
    // @JoinColumn(name = "SkillId")
    // private Skill skill;

    @Column(name = "ProjectId", nullable = false)
    private int projectId;

    @Column(name = "SkillId", nullable = false)
    private int skillId;

    // private int SkillName;

    @Column(name = "Level", nullable = false)
    private int level;

    // private int status;
}
