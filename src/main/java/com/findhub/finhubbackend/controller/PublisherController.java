package com.findhub.finhubbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.publisher.Publisher;
import com.findhub.finhubbackend.entity.publisher.PublisherStatus;
import com.findhub.finhubbackend.model.PublisherResponseModel;
import com.findhub.finhubbackend.service.publisher.PublisherService;
import com.findhub.finhubbackend.util.Config.ApiPath;
import com.findhub.finhubbackend.util.Config.Var;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.PUBLISHER)
public class PublisherController extends ApiController<Publisher, PublisherService, PublisherStatus> {

    @Override
    public ResponseEntity<?> get(@PathVariable(Var.ID) int id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getResponseModelById(id));
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Publisher> publishers = service.getAll();

        if (publishers.isEmpty())
            return errorResponse("No" + entityName + "found",
                    HttpStatus.NOT_FOUND);

        List<PublisherResponseModel> prm = new ArrayList<>();
        publishers.forEach(each -> prm.add(service.getResponseModelById(each.getId())));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(prm);
    }
}
