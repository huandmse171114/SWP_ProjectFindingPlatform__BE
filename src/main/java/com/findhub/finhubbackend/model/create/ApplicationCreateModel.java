package com.findhub.finhubbackend.model.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationCreateModel {
    // int getLeaderId();
    private int projectId;
    private int teamId;
    private String message;
}
