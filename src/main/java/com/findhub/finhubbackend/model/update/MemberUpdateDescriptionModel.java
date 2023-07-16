package com.findhub.finhubbackend.model.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberUpdateDescriptionModel {
	private int id;
	private String email;
	private String description;
}
