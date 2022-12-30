package com.lab.application.controllers;

import com.lab.application.models.dtos.CategoryDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categories")
public interface CategoryController {
    @PostMapping("/add/category")
    void addCategory(@RequestBody CategoryDTO categoryDTO,
                     Authentication authentication);

    @DeleteMapping("/remove/category/{category_uuid}")
    void removeCategory(@PathVariable("category_uuid") String categoryUUID, Authentication authentication);

    @GetMapping("/get/all/categories")
    List<CategoryDTO> getAllUserCategories(Authentication authentication);

    @GetMapping("/get/categories/by/description/{description}")
    List<CategoryDTO> getUserCategoryByType(@PathVariable("description") String description,
                                            Authentication authentication);
}
