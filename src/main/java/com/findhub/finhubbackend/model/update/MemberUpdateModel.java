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
public class MemberUpdateModel {
    private int memberId;
    private String email;
    private String phone;
    private String name;
    private String description;
    private int majorId;
    private String dob;
    private List<MemberSkillUpdateModel> skills;
}
