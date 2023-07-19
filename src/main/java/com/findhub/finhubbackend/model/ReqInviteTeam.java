package com.findhub.finhubbackend.model;

import com.findhub.finhubbackend.model.model.StatusModel;
import com.findhub.finhubbackend.model.response.CategoryResponseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqInviteTeam {
	private int id;
	private String name;
	private ReqInviteTeamLeadModel leader;
}
