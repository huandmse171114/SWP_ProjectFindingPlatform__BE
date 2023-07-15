package com.findhub.finhubbackend.model.response;

import java.util.List;

import com.findhub.finhubbackend.model.model.StatusModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillResponseModel {
	private int id;
	private String name;
	private StatusModel status;
}
