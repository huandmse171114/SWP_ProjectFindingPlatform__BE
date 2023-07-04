package com.findhub.finhubbackend.model.response;

import java.util.List;

import com.findhub.finhubbackend.entity.publisher.Publisher;
import com.findhub.finhubbackend.model.model.DeliverableTypeModel;
import com.findhub.finhubbackend.model.model.SkillModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponseModel {
    private int id;
    private String name;
    private String description;
    private Publisher publisher;
    private List<SkillModel> skills;
    private String publishDate;
    private String imageURL;
    private int deliverDays;
    private float wage;
    private String dueDate;
    private List<String> categories;
    private List<DeliverableTypeModel> outputs;
    private String status;
}
