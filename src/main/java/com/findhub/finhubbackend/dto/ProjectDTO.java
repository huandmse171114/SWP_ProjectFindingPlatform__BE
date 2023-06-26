package com.findhub.finhubbackend.dto;

import java.sql.Date;

public interface ProjectDTO {
    int getId();

    String getName();

    int getPublisherId();

    String getDescription();

    float getWage();

    String getImageURL();

    Date getPublishDate();

    int getStatus();
}
