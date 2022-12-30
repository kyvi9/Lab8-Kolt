package com.lab.application.models.dtos;


import com.lab.application.models.entities.MoneyManipulation;
import com.lab.application.models.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@NoArgsConstructor
public class CategoryDTO extends BaseEntityDTO {
    private String description;
    private Set<MoneyManipulation> manipulations;
    private User user;
}
