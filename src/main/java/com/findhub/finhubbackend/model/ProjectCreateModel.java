package com.findhub.finhubbackend.model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectCreateModel {
    private String title;
    private String type;
    private String description;
    private float wage;
    private MultipartFile imageFile;
    private int deliverDays;
    private Date publishDate;
}
