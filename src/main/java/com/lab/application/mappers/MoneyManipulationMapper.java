package com.lab.application.mappers;

import com.lab.application.models.dtos.MoneyManipulationDTO;
import com.lab.application.models.entities.MoneyManipulation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoneyManipulationMapper
{
    MoneyManipulation moneyManipulationDTOtoMoneyManipulation(MoneyManipulationDTO moneyManipulationDTO);

    MoneyManipulationDTO moneyManipulationToMoneyManipulationDTO(MoneyManipulation moneyManipulation);
}
