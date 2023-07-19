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
public class TeamResponseModel {
    private int id;
    private String name;
    // private PublisherDTO publisher;
    private List<TeamMemberResponseModel> members;
    private float balance;
    private StatusModel status;
    private List<Project> projects;
    private List<TeamReqRequestResponseModel> requests;
    private Project project;
}
