package com.findhub.finhubbackend.model;

import java.sql.Date;
import java.util.List;

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
    private List<String> skills;
    private Date publishDate;
    private int deliverDays;
    private float wage;
    private Date dueDate;
    private List<String> categories;
    private List<String> delivarableTypes;
    private String status;
}
