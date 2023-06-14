package com.findhub.finhubbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.category.Category;
import com.findhub.finhubbackend.entity.category.CategoryStatus;
import com.findhub.finhubbackend.service.category.CategoryService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.CATEGORY)
public class CategoryController extends ApiController<Category, CategoryService, CategoryStatus> {

    @PostMapping(ApiPath.ENABLE)
    public boolean enableEntity(@RequestBody int id) {
        return service.updateStatus(id, CategoryStatus.ACTIVE);
    }

    @PostMapping(ApiPath.DISABLE)
    public boolean restoreEntity(@RequestBody int id) {
        return service.updateStatus(id, CategoryStatus.INACTIVE);
    }
}
