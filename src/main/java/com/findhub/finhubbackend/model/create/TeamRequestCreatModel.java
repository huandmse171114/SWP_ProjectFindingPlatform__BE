package com.findhub.finhubbackend.model.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamRequestCreatModel {
	private int teamId;
	private int memberId;
	private String message;
}
