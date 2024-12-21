package com.xinchao.controllers;

import com.xinchao.endpoints.CategoryApiEndpoints;
import com.xinchao.models.Category;
import com.xinchao.security.AdminPermission;
import com.xinchao.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CategoryApiEndpoints.BASE_URL_CATEGORY)
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> statuses = categoryService.getAllCategory();
        return ResponseEntity.ok(statuses);
    }
    @CrossOrigin(origins = "*")
    @PostMapping
    @AdminPermission
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category newCategory = categoryService.addCategory(category);
        return ResponseEntity.ok(newCategory);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping(CategoryApiEndpoints.DELETE_CATEGORY)
    @AdminPermission
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted) {
            return ResponseEntity.ok("Category deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Category not found");
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(CategoryApiEndpoints.GET_CATEGORY_BY_ID)
    @AdminPermission
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        Optional<Category> category = categoryService.getCategoryByName(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
}
