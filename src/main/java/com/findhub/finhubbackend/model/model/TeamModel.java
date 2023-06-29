package com.findhub.finhubbackend.model.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamModel {
    private int id;
    private String name;
    private float balance;
    private String status;
    private List<MemberTeamModel> members;
}
