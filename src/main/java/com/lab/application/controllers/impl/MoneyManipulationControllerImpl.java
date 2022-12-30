package com.lab.application.controllers.impl;

import com.lab.application.controllers.MoneyManipulationController;
import com.lab.application.models.dtos.MoneyManipulationDTO;
import com.lab.application.models.enums.MoneyManipulationColorEnum;
import com.lab.application.models.enums.MoneyManipulationTypeEnum;
import com.lab.application.services.MoneyManipulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MoneyManipulationControllerImpl implements MoneyManipulationController
{
    private final MoneyManipulationService moneyManipulationService;

    @Override
    public MoneyManipulationDTO addManipulation(MoneyManipulationDTO moneyManipulationDTO, Authentication authentication)
    {
        return moneyManipulationService.add(moneyManipulationDTO, authentication);
    }

    @Override
    public void removeManipulation(String manipulationUUID, Authentication authentication)
    {
        moneyManipulationService.remove(manipulationUUID, authentication);
    }

    @Override
    public List<MoneyManipulationDTO> getAllMoneyManipulation(Authentication authentication)
    {
        return moneyManipulationService.getAll(authentication);
    }

    @Override
    public List<MoneyManipulationDTO> getMoneyManipulationByType(String type, Authentication authentication)
    {
        return moneyManipulationService.getByType(authentication, MoneyManipulationTypeEnum.valueOf(type));
    }

    @Override
    public List<MoneyManipulationDTO> getMoneyManipulationByColor(String color, Authentication authentication) {
        return moneyManipulationService.getByColor(authentication, MoneyManipulationColorEnum.valueOf(color));
    }

    @Override
    public List<MoneyManipulationDTO> getMoneyManipulationByTypeAndColor(String type, String color, Authentication authentication) {
        return moneyManipulationService.getByTypeAndColor(authentication,
                                                          MoneyManipulationTypeEnum.valueOf(type),
                                                          MoneyManipulationColorEnum.valueOf(color));
    }
}
