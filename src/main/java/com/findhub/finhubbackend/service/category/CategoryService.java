package com.findhub.finhubbackend.service.category;

import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.dto.CategoryDTO;
import com.findhub.finhubbackend.entity.category.Category;
import com.findhub.finhubbackend.entity.category.CategoryStatus;
import com.findhub.finhubbackend.model.response.CategoryResponseModel;
import com.findhub.finhubbackend.model.update.CategoryUpdateModel;
import com.findhub.finhubbackend.service.service.Service;

public interface CategoryService extends Service<Category, CategoryStatus> {

    /**
     * tìm chính xác name
     */
    public Optional<Category> findByName(String name);

    /**
     * tìm tất cả Category có chính xác
     */
    public List<Category> findAllByNameContaining(String name);

    public List<CategoryDTO> getNameByProjectId(int id);

    public boolean existsByName(String name);
    
    public CategoryResponseModel getModel(int id);

	public boolean update(CategoryUpdateModel categoryModel);

}
