package com.xinchao.services;

import com.xinchao.models.Category;

import com.xinchao.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public boolean deleteCategory(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.deleteById(id);
            return true;
        } else {
            return false; // Status not found
        }
    }

    public Optional<Category> getCategoryByName(Integer id) {
        return categoryRepository.findById(id);
    }
    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }
}
