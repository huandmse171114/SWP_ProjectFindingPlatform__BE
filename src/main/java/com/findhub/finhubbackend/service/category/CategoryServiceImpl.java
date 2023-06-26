package com.findhub.finhubbackend.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.findhub.finhubbackend.dto.CategoryDTO;
import com.findhub.finhubbackend.entity.category.Category;
import com.findhub.finhubbackend.entity.category.CategoryStatus;
import com.findhub.finhubbackend.repository.CategoryRepository;
import com.findhub.finhubbackend.service.service.ServiceImpl;

@Service
public class CategoryServiceImpl extends ServiceImpl<Category, CategoryRepository, CategoryStatus>
        implements CategoryService {

    @Override
    public List<CategoryDTO> getNameByProjectId(int id) {
        return repo.getNameByProjectId(id);
    }

    @Override
    public List<Category> findAllByNameContaining(String name) {
        return repo.findAllByNameContaining(name);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public boolean existsByName(String name) {
        return repo.existsByName(name);
    }

}
