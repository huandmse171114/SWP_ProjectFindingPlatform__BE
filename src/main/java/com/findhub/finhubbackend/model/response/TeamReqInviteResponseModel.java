package com.findhub.finhubbackend.model.response;

import com.findhub.finhubbackend.model.ReqInviteTeam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamReqInviteResponseModel {
	private int id;
	private String createDate;
	private String message;
	private ReqInviteTeam team;
}
