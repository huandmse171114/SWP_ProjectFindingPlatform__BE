package com.findhub.finhubbackend.model.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OutputModel {
    // private int projectId;
    private String name;
    private String description;
    private int outputId;
    private int status;
}
