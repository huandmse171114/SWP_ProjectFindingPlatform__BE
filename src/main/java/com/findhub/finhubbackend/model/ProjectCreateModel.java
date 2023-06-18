package com.findhub.finhubbackend.model;

import java.sql.Date;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.findhub.finhubbackend.entity.category.Category;
import com.findhub.finhubbackend.entity.deliverableType.DeliverableType;
import com.findhub.finhubbackend.entity.skill.Skill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectCreateModel {
    private String name;
    private String description;
    private float wage;
    private Date publishDate;
    private int deliverDays;
    private String imageURL;
    private Set<Skill> skillSet;
    private Set<Category> categorySet;
    private Set<DeliverableType> deliverableTypeSet;
}
