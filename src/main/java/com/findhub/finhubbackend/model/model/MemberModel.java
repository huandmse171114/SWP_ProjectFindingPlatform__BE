package com.findhub.finhubbackend.model.model;

import java.sql.Date;
import java.util.List;

import com.findhub.finhubbackend.model.response.MajorResponseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberModel {
    private int id;
    private String email;
    private String name;
    private String description;
    private Date DOB;
    private String phone;
    private float balance;
    private MajorResponseModel major;
    private String status;
    private String role;

    // extend infomation
    private List<SkillModel> skills;
}