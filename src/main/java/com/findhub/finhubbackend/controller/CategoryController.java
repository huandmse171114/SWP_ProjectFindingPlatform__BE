package com.findhub.finhubbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    


    @PostMapping()
    public ResponseEntity<?> create(@RequestBody String name) {
        if (service.existsByName(name))
            return new ResponseEntity<>("Category[name=\'" + name + "\'] already existed", HttpStatus.FOUND);

        Category category = Category
            .builder()
                .name(name)
            .build();

        service.save(category);
        // return new ResponseEntity<>("Added new Category[name=\'" + name + "\'] successfully", HttpStatus.OK);

        return super.create(category);
    }
}
