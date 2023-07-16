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
import com.findhub.finhubbackend.entity.major.Major;
import com.findhub.finhubbackend.entity.major.MajorStatus;
import com.findhub.finhubbackend.exception.EntityNotFoundException;
import com.findhub.finhubbackend.model.create.MajorCreateModel;
import com.findhub.finhubbackend.model.response.CategoryResponseModel;
import com.findhub.finhubbackend.model.response.MajorResponseModel;
import com.findhub.finhubbackend.model.update.CategoryUpdateModel;
import com.findhub.finhubbackend.model.update.MajorUpdateModel;
import com.findhub.finhubbackend.service.major.MajorService;
import com.findhub.finhubbackend.util.Config.ApiPath;

@RestController
@CrossOrigin
@RequestMapping(path = ApiPath.MAJOR)
public class MajorController extends ApiController<Major, MajorService, MajorStatus> {
	
	private List<MajorResponseModel> getResponseModels(List<Major> majors) {
		if (majors.isEmpty())
			throw new EntityNotFoundException("No Major found");

		List<MajorResponseModel> result = new ArrayList<>();
		majors.forEach(category -> {
			result.add(service.getModel(category.getId()));
		});
		return result;
	}

	@PostMapping()
	public ResponseEntity<?> create(@RequestBody MajorCreateModel model) {
		String code = model.getCode();
		String name = model.getName();
		
		if (code.isBlank() || name.isBlank()) {
			return new ResponseEntity<>("Please fill in all fields", HttpStatus.EXPECTATION_FAILED);
		}

		if (service.existsByCode(code))
			return new ResponseEntity<>("Major code [" + code + "] already existed", HttpStatus.FOUND);

		if (service.existsByName(name))
			return new ResponseEntity<>("Major name [" + name + "] already existed", HttpStatus.FOUND);
		if (code.length() > 2) {
			return new ResponseEntity<>("Major code only has 2 characters length", HttpStatus.EXPECTATION_FAILED);
		}
		Major major = Major
			.builder()
				.code(code)
				.name(name)
			.build();

		service.save(major);
		return new ResponseEntity<>(
			"Added new Major[code=" + code + "; name=\'" + name + "\'] successfully",
			HttpStatus.OK
		);
	}
	
	@Override
    public ResponseEntity<?> getAll() {
    	return ResponseEntity
				.status(HttpStatus.OK)
				.body(getResponseModels(service.getAll()));
    }
	
	@PutMapping()
    public ResponseEntity<String> update(@RequestBody MajorUpdateModel majorModel) {
    	if (service.existsByName(majorModel.getName()))
            return new ResponseEntity<>("Major name [" + majorModel.getName() + "] already existed", HttpStatus.FOUND);
        
    	if (service.existsByCode(majorModel.getCode()))
            return new ResponseEntity<>("Major code [" + majorModel.getCode() + "] already existed", HttpStatus.FOUND);
        
    	
        if(majorModel.getName().isBlank() || majorModel.getCode().isBlank()) {
        	return new ResponseEntity<>("Please fill in all fields", HttpStatus.EXPECTATION_FAILED);
        }

            if(service.update(majorModel)) {
            	return new ResponseEntity<>("Update Major successfully", HttpStatus.OK);            	
            }else {
            	return new ResponseEntity<>("Update Major failed", HttpStatus.FAILED_DEPENDENCY);   
            }
    }
}
