package com.findhub.finhubbackend.entity.upload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class upload {
    private String fileName;
    private String downloadUri;
    private long size;
}
