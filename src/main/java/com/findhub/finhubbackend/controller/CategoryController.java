package com.findhub.finhubbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.category.Category;
import com.findhub.finhubbackend.entity.category.CategoryStatus;
import com.findhub.finhubbackend.service.category.CategoryService;
import com.findhub.finhubbackend.util.Config.*;

@RestController
@CrossOrigin
@RequestMapping(path = Path.CATEGORY)
public class CategoryController extends MyController<Category, CategoryService, CategoryStatus> {

    @PostMapping(Path.ENABLE)
    public boolean enableEntity(@RequestBody int id) {
        return service.changeStatus(id, CategoryStatus.ACTIVE);
    }

    @PostMapping(Path.DISABLE)
    public boolean restoreEntity(@RequestBody int id) {
        return service.changeStatus(id, CategoryStatus.INACTIVE);
    }

    public boolean changeStatusEntity(@RequestBody int id, @RequestBody int status) {
		return service.changeStatus(id, status);
	}
}
