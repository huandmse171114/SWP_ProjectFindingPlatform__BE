package com.findhub.finhubbackend.model.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private int status;
    private String message;
    private Map<String, Object> cause;

    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
