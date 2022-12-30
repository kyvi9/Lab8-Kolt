package com.lab.application.services;

import com.lab.application.models.dtos.CategoryDTO;
import com.lab.application.models.entities.Category;
import lombok.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CategoryService {
    Category add(@NonNull CategoryDTO categoryDTO, @NonNull Authentication authentication);

    void remove(@NonNull String categoryUUID);

    List<CategoryDTO> getAllCategories(@NonNull Authentication authentication);

    List<CategoryDTO> getByDescription(@NonNull Authentication authentication, @NonNull String description);
}
