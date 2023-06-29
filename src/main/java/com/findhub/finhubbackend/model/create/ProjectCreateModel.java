package com.findhub.finhubbackend.model.create;

import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.findhub.finhubbackend.model.model.ProjectDeliverableModel;
import com.findhub.finhubbackend.model.model.SkillModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectCreateModel {
    @NotNull(message = "name should not be null")
    private String name;

    private String description;

    private int publisherId;

    @Min(0)
    private float wage;

    private String publishDate;
    private String dueDate;

    @Min(0)
    private int deliverDays;

    private String imageURL;
    private int status;

    private Set<SkillModel> skills;

    private Set<Integer> categories;

    private Set<ProjectDeliverableModel> outputs;
}
