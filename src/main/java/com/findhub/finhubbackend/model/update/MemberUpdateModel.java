package com.findhub.finhubbackend.model.update;

import com.findhub.finhubbackend.model.model.StatusModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberUpdateModel {
    private int id;
    private String name;
    private String phone;
    private String dob;
    private String email;
    private String avatarURL;
    private int majorId;
    private StatusModel status;
}
