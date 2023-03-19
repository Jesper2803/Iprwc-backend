package com.example.Iprwcbackend.module.category;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public void addNewCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException("category with id " + categoryId + " does not exist"));
        categoryRepository.deleteById(categoryId);
    }

    @Transactional
    public void updateCategory(Long categoryId, String categoryName) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalStateException("category with id " + categoryId + " does not exist"));
        if (categoryName != null && categoryName.length() > 0){
            category.setCategoryName(categoryName);
        }
    }
}
