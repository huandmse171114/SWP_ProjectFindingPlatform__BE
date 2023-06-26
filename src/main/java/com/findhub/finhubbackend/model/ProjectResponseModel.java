package com.findhub.finhubbackend.model;

import java.util.List;

import com.findhub.finhubbackend.dto.SkillDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponseModel {
    private int id;
    private String name;
    private String description;
    private List<SkillDTO> skills;
    private String publishDate;
    private int deliverDays;
    private float wage;
    private String dueDate;
    private List<String> categories;
    private List<String> delivarableTypes;
    private String status;
}
