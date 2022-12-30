package com.lab.application.services;

import com.lab.application.models.dtos.MoneyManipulationDTO;
import com.lab.application.models.enums.MoneyManipulationColorEnum;
import com.lab.application.models.enums.MoneyManipulationTypeEnum;
import lombok.NonNull;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface MoneyManipulationService
{
    MoneyManipulationDTO add(@NonNull MoneyManipulationDTO moneyManipulationDTO, @NonNull Authentication authentication);

    void remove(@NonNull String moneyManipulation, @NonNull Authentication authentication);

    List<MoneyManipulationDTO> getAll(@NonNull Authentication authentication);

    List<MoneyManipulationDTO> getByType(@NonNull Authentication authentication,
                                         @NonNull MoneyManipulationTypeEnum moneyManipulationType);

    List<MoneyManipulationDTO> getByColor(@NonNull Authentication authentication,
                                          @NonNull MoneyManipulationColorEnum moneyManipulationColor);

    List<MoneyManipulationDTO> getByTypeAndColor(@NonNull Authentication authentication,
                                                 @NonNull MoneyManipulationTypeEnum moneyManipulationType,
                                                 @NonNull MoneyManipulationColorEnum moneyManipulationColor);
}
