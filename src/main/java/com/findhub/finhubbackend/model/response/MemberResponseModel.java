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
public class MemberResponseModel {
	private int id;
	private String email;
	private String fullname;
	private String phone;
	private MajorResponseModel major;
	private StatusModel status;
	 
}
