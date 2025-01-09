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

    public Optional<Category> updateCategoryName(Integer id, String newName) {
        // Kiểm tra xem Category có tồn tại hay không
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) {
            throw new IllegalArgumentException("Category not found with id: " + id);
        }

        // Kiểm tra xem tên mới đã tồn tại chưa
        if (categoryRepository.existsByName(newName)) {
            throw new IllegalArgumentException("Category name '" + newName + "' already exists");
        }

        // Nếu hợp lệ, tiến hành cập nhật
        Category category = categoryOptional.get();
        category.setName(newName); // Cập nhật tên
        return Optional.of(categoryRepository.save(category)); // Lưu thay đổi
    }


    public Optional<Category> getCategoryByName(Integer id) {
        return categoryRepository.findById(id);
    }
    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }
}
