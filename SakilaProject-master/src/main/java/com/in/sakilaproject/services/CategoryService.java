package com.in.sakilaproject.services;

import com.in.sakilaproject.entities.Category;
import com.in.sakilaproject.respositories.CategoryRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getByCategoryId(Integer id){
        return categoryRepository.getCategoryByCategoryId(id);
    }

}
