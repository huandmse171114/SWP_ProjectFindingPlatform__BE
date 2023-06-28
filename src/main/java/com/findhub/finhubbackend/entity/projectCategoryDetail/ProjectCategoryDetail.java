package com.findhub.finhubbackend.entity.projectCategoryDetail;

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
@Table(name = "ProjectCategoryDetail", uniqueConstraints = @UniqueConstraint(columnNames = {
        "CategoryId",
        "ProjectId"
}))
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectCategoryDetail extends MyEntity {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ProjectId", nullable = false)
    private int projectId;

    @Column(name = "CategoryId", nullable = false)
    private int categoryId;

    // private int status;
}
