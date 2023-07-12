package com.findhub.finhubbackend.model.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberTeamModel {
    private int id;
    private String name;
    private String role;
    private int status;
}
