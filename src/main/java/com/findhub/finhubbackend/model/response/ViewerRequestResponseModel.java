package com.findhub.finhubbackend.model.response;

import java.util.List;

import com.findhub.finhubbackend.entity.project.Project;
import com.findhub.finhubbackend.model.model.StatusModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewerRequestResponseModel {
	private int id;
	private String createDate;
	private String message;
	private StatusModel status;
}
