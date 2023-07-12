package com.findhub.finhubbackend.model.update;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectUpdateModel {
    private int id;
    private String name;
    private String description;
    private int publisherId;
    private List<ProjectSkillUpdateModel> skills;
    private String publishDate;
    private String imageURL;
    private int deliverDays;
    private float wage;
    private String dueDate;
    private List<ProjectCategoryUpdateModel> categories;
    private List<ProjectDeliverableUpdateModel> deliverables;
    private int status;
}
