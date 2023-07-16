package com.findhub.finhubbackend.model.update;

import com.findhub.finhubbackend.model.model.StatusModel;
import com.findhub.finhubbackend.model.response.MajorResponseModel;
import com.findhub.finhubbackend.model.response.MemberResponseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillUpdateModel {
    private String name;
    private int id;
}
