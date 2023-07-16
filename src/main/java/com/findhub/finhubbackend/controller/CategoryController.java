package com.findhub.finhubbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findhub.finhubbackend.entity.category.Category;
import com.findhub.finhubbackend.entity.category.CategoryStatus;
import com.findhub.finhubbackend.entity.skill.Skill;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.create.CategoryCreateModel;
import com.findhub.finhubbackend.model.response.CategoryResponseModel;
import com.findhub.finhubbackend.model.response.SkillResponseModel;
import com.findhub.finhubbackend.model.update.CategoryUpdateModel;
import com.findhub.finhubbackend.model.update.SkillUpdateModel;
import com.findhub.finhubbackend.service.category.CategoryService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.CATEGORY)
public class CategoryController extends ApiController<Category, CategoryService, CategoryStatus> {
	
	private List<CategoryResponseModel> getResponseModels(List<Category> categories) {
		if (categories.isEmpty())
			throw new EntityNotFoundException("No Category found");

		List<CategoryResponseModel> result = new ArrayList<>();
		categories.forEach(category -> {
			result.add(service.getModel(category.getId()));
		});
		return result;
	}

    


    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CategoryCreateModel categoryModel) {
        if (service.existsByName(categoryModel.getName()))
            return new ResponseEntity<>("Category [" + categoryModel.getName() + "] already existed", HttpStatus.FOUND);

        if(categoryModel.getName().isBlank()) {
        	return new ResponseEntity<>("Please fill in all fields", HttpStatus.EXPECTATION_FAILED);
        }
        
        Category category = Category
            .builder()
                .name(categoryModel.getName())
            .build();

        service.save(category);
        return new ResponseEntity<>("Added new Category [" + categoryModel.getName() + "] successfully", HttpStatus.OK);

//        return super.create(category);
    }
    
    @Override
    public ResponseEntity<?> getAll() {
    	return ResponseEntity
				.status(HttpStatus.OK)
				.body(getResponseModels(service.getAll()));
    }
    
    @PutMapping()
    public ResponseEntity<String> update(@RequestBody CategoryUpdateModel categoryModel) {
    	if (service.existsByName(categoryModel.getName()))
            return new ResponseEntity<>("Category " + categoryModel.getName() + " already existed", HttpStatus.FOUND);
        
        if(categoryModel.getName().isBlank()) {
        	return new ResponseEntity<>("Please fill in all fields", HttpStatus.EXPECTATION_FAILED);
        }

            if(service.update(categoryModel)) {
            	return new ResponseEntity<>("Update Category successfully", HttpStatus.OK);            	
            }else {
            	return new ResponseEntity<>("Update Category failed", HttpStatus.FAILED_DEPENDENCY);   
            }
    }
}
