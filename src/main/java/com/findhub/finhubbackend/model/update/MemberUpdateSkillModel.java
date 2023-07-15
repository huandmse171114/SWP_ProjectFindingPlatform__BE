package com.findhub.finhubbackend.model.update;

import java.util.List;

import com.findhub.finhubbackend.model.model.MemberSkillModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberUpdateSkillModel {
	private int id;
	private String email;
	private List<MemberSkillModel> skills;
}
