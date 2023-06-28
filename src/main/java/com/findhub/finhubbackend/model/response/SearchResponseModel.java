package com.findhub.finhubbackend.model.response;

import java.util.List;

import com.findhub.finhubbackend.dto.MajorDTO;
import com.findhub.finhubbackend.dto.MemberDTO;
import com.findhub.finhubbackend.dto.PublisherDTO;
import com.findhub.finhubbackend.dto.TeamDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchResponseModel {
    private List<ProjectResponseModel> projects;
    private List<String> skills;
    private List<MemberDTO> members;
    private List<PublisherDTO> publishers;
    private List<TeamDTO> teams;
    private List<String> categories;
    private List<MajorDTO> majors;
}
