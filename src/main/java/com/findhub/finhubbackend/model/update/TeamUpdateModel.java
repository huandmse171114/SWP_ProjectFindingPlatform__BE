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
public class TeamUpdateModel {
    private int id;
    private int status;
    private String name;
    private List<TeamMemberUpdateModel> members;
}
