package com.findhub.finhubbackend.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectSkillResponseModel {
    private int id;
    private int projectSkillId;
    private String name;
    private int level;
    private String status;
}
