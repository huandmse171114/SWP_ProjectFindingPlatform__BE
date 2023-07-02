package com.findhub.finhubbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.output.Output;
import com.findhub.finhubbackend.entity.output.OutputStatus;
import com.findhub.finhubbackend.service.output.OutputService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.OUTPUT)
public class OutputController
        extends ApiController<Output, OutputService, OutputStatus> {

    @PostMapping()
    public ResponseEntity<?> add(@RequestBody String name) {
        if (service.existsByName(name))
            return new ResponseEntity<>("DeliverableType[name=\'" + name + "\'] already existed", HttpStatus.FOUND);

        Output output = Output.builder()
                .name(name)
                .build();

        service.save(output);
        return new ResponseEntity<>("Added new DeliverableType[name=\'" + name + "\'] successfully", HttpStatus.OK);
    }
}
