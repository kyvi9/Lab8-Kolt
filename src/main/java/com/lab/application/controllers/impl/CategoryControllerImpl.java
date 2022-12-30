package com.lab.application.controllers.impl;

import com.lab.application.controllers.CategoryController;
import com.lab.application.models.dtos.CategoryDTO;
import com.lab.application.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;


    @Override
    public void addCategory(CategoryDTO categoryDTO, Authentication authentication) {
        categoryService.add(categoryDTO, authentication);
    }

    @Override
    public void removeCategory(String categoryUUID, Authentication authentication) {
        categoryService.remove(categoryUUID);
    }

    @Override
    public List<CategoryDTO> getAllUserCategories(Authentication authentication) {
        return categoryService.getAllCategories(authentication);
    }

    @Override
    public List<CategoryDTO> getUserCategoryByType(String description, Authentication authentication) {
        return categoryService.getByDescription(authentication, description);
    }

}
