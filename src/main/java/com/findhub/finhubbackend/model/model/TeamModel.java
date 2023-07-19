package com.findhub.finhubbackend.model.model;

import java.util.List;

import com.findhub.finhubbackend.model.response.ViewerRequestResponseModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamModel {
    private int id;
    private String name;
    private float balance;
    private String status;
    private List<MemberTeamModel> members;
    private ViewerRequestResponseModel request;
}
