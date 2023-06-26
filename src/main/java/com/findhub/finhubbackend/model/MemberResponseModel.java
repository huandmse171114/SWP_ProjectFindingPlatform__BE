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
public class MemberResponseModel {
    private int id;
    private String email;
    private String name;
    private String description;
    private Date DOB;
    private String phone;
    private float balance;
    private MajorResponseModel major;
    private String status;

    //extend infomation
    private List<SkillRepsonseModel> skills;
}
