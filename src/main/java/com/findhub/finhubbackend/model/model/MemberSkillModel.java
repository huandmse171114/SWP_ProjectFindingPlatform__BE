package com.findhub.finhubbackend.model.model;

import com.findhub.finhubbackend.model.update.CategoryUpdateModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberSkillModel {
	private int id;
	private int level;
	private Integer status;
}
