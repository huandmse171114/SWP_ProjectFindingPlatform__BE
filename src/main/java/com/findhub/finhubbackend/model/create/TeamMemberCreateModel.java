package com.findhub.finhubbackend.model.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamMemberCreateModel {
    private int id;
    // private String name;
    private int role;
    private int status;
}
