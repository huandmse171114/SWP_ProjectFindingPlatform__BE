package com.findhub.finhubbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.deliverableType.DeliverableType;
import com.findhub.finhubbackend.entity.deliverableType.DeliverableTypeStatus;
import com.findhub.finhubbackend.service.deliverableType.DeliverableTypeService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.DELIVERABLE_TYPE)
public class DeliverableTypeController
        extends ApiController<DeliverableType, DeliverableTypeService, DeliverableTypeStatus> {

    @PostMapping(ApiPath.ENABLE)
    public boolean enableEntity(@RequestBody int id) {
        return service.updateStatus(id, DeliverableTypeStatus.ACTIVE);
    }

    @PostMapping(ApiPath.DISABLE)
    public boolean disableEntity(@RequestBody int id) {
        return service.updateStatus(id, DeliverableTypeStatus.INACTIVE);
    }
}
