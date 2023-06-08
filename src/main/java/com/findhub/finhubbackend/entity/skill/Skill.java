package com.findhub.finhubbackend.entity.skill;

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
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "Skill", uniqueConstraints = @UniqueConstraint(columnNames = "SkillName"))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "SkillName", columnDefinition = "nvarchar", nullable = false)
    private String name;

    @Column(name = "Status", nullable = false)
    private int status;
}
