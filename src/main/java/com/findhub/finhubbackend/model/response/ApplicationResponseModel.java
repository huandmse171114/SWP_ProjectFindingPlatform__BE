package com.findhub.finhubbackend.model.response;

import com.findhub.finhubbackend.model.model.StatusModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationResponseModel {
	private int id;
	private String message;
	private String createDate;
	private TeamApplicationResponseModel team;
}
