package com.lab.application.models.dtos;

import com.lab.application.models.enums.MoneyManipulationColorEnum;
import com.lab.application.models.enums.MoneyManipulationTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class MoneyManipulationDTO extends BaseEntityDTO
{
    private BigDecimal moneyAmount;
    private MoneyManipulationTypeEnum type;
    private MoneyManipulationColorEnum color;
}
