package com.findhub.finhubbackend.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamInProjectResponseModel {
	private int id;
	private String name;
	private String description;
	private List<TeamMemberResponseModel> members;
}
