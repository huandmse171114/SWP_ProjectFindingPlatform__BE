package com.findhub.finhubbackend.model.update;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamMemberUpdateModel {
    private int teamId;
    // private int teamMemberId;
    private int memberId;
    private String name;
    private int role;
    private int status;
}