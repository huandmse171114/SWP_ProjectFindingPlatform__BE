package com.findhub.finhubbackend.model.create;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamCreateModel {
    private String name;
    private List<TeamMemberCreateModel> members;
}
