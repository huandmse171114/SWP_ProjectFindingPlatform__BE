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
public class TeamReqRequestResponseModel {
	private int id;
	private MemberRequestResponseModel member;
	private String createDate;
	private String message;
}
