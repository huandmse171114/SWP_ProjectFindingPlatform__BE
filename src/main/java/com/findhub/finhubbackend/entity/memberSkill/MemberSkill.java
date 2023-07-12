package com.findhub.finhubbackend.entity.memberSkill;

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
import com.findhub.finhubbackend.entity.member.Member;
import com.findhub.finhubbackend.entity.skill.Skill;

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
    name = "Member_Skill",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {
            "SkillId",
            "MemberId"
        }
    )
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class MemberSkill extends MyEntity{

    @Id
    @Column(
        name = "Id",
        nullable = false
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
    )
    @JsonBackReference
    @JoinColumn(name = "MemberId")
    private Member member;

    @ManyToOne(
        fetch = FetchType.EAGER,
        cascade = CascadeType.ALL
    )
    @JsonBackReference
    @JoinColumn(name = "SkillId")
    private Skill skill;

    @Default
    @Column(
        name = "Level",
        nullable = false
    )
    private int level = 0;

    @Default
    @Column(
        name = "Status",
        nullable = false
    )
    private int status = MemberSkillStatus.ACTIVE.getValue();
}
