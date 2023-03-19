package com.example.Iprwcbackend.module.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @PostMapping("/admin/new")
    public void registerNewCategory(@RequestBody Category category){
        categoryService.addNewCategory(category);
    }

    @DeleteMapping(path = "/admin/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);
    }

    @PutMapping(path = "/admin/{categoryId}")
    public void updateCategory(@PathVariable("categoryId") Long categoryId,
                           @RequestBody Category category){
        String categoryName = category.getCategoryName();
        categoryService.updateCategory(categoryId, categoryName);
    }
}