package com.findhub.finhubbackend.service.category;

import com.findhub.finhubbackend.entity.category.CategoryStatus;
import com.findhub.finhubbackend.service.service.Service;

import java.util.List;
import java.util.Optional;

import com.findhub.finhubbackend.entity.category.Category;

public interface CategoryService extends Service<Category, CategoryStatus> {

    /**
     * tìm chính xác name
     */
    public Optional<Category> findByName(String name);

    /**
     * tìm tất cả Category có chính xác
     */
    public List<Category> findAllByNameLike(String name);
}
