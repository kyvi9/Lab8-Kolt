package com.lab.application.mappers;

import com.lab.application.models.dtos.CategoryDTO;
import com.lab.application.models.entities.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(target = "description", source = "categoryDTO.description")
    })
    Category categoryDTOtoCategory(CategoryDTO categoryDTO);
    @Mappings({
            @Mapping(target = "description", ignore = true)
    })
    CategoryDTO categoryToCategoryDTO(Category category);

    String getDescription(Category c);
}
