package com.findhub.finhubbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.deliverableType.DeliverableType;
import com.findhub.finhubbackend.service.deliverable.DeliverableService;
import com.findhub.finhubbackend.entity.deliverableType.DeliverableStatus;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.OUTPUT)
public class DeliverableController
        extends ApiController<DeliverableType, DeliverableService, DeliverableStatus> {

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody String name) {
        if (service.existsByName(name))
            return new ResponseEntity<>("DeliverableType[name=\'" + name + "\'] already existed", HttpStatus.FOUND);

        DeliverableType output = DeliverableType
            .builder()
                .name(name)
            .build();

        service.save(output);
        return new ResponseEntity<>("Added new DeliverableType[name=\'" + name + "\'] successfully", HttpStatus.OK);
    }
}
