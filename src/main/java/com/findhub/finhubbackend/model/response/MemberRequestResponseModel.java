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
public class MemberRequestResponseModel {
	private int id;
	private String name;
	private String avatarURL;
	private String email;
}
