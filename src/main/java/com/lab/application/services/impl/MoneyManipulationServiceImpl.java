package com.lab.application.services.impl;

import com.lab.application.exeptions.MoneyManipulationServiceException;
import com.lab.application.mappers.MoneyManipulationMapper;
import com.lab.application.models.dtos.MoneyManipulationDTO;
import com.lab.application.models.entities.MoneyManipulation;
import com.lab.application.models.entities.User;
import com.lab.application.models.enums.MoneyManipulationColorEnum;
import com.lab.application.models.enums.MoneyManipulationTypeEnum;
import com.lab.application.repositories.MoneyManipulationRepository;
import com.lab.application.services.MoneyManipulationService;
import com.lab.application.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoneyManipulationServiceImpl implements MoneyManipulationService
{
    private final MoneyManipulationRepository moneyManipulationRepository;
    private final MoneyManipulationMapper moneyManipulationMapper;
    private final UserService userService;

    @Override
    public MoneyManipulationDTO add(MoneyManipulationDTO moneyManipulationDTO, Authentication authentication)
    {

        MoneyManipulation moneyManipulation = moneyManipulationMapper
                .moneyManipulationDTOtoMoneyManipulation(moneyManipulationDTO);

        User user;

        if(moneyManipulation.getType().equals(MoneyManipulationTypeEnum.INCOME))
            user = userService.addMoney(authentication, moneyManipulationDTO.getMoneyAmount());
        else if(moneyManipulation.getType().equals(MoneyManipulationTypeEnum.COSTS))
            user = userService.withdrawMoney(authentication, moneyManipulationDTO.getMoneyAmount());
        else
            throw new MoneyManipulationServiceException(MoneyManipulationServiceException.WRONG_MONEY_MANIPULATION_TYPE);


        moneyManipulation.setUser(user);

        return moneyManipulationMapper
                .moneyManipulationToMoneyManipulationDTO(moneyManipulationRepository.save(moneyManipulation));
    }

    @Override
    @Transactional
    public void remove(String moneyManipulationUUID, Authentication authentication)
    {
        moneyManipulationRepository.getMoneyManipulationByEntityUUIDAndUserEmail(moneyManipulationUUID, authentication.getName())
                .map(moneyManipulation -> {
                    userService.removeMoneyManipulation(moneyManipulation);
                    moneyManipulationRepository.delete(moneyManipulation);
                    return moneyManipulation;
                })
                .orElseThrow(() -> new MoneyManipulationServiceException(MoneyManipulationServiceException.REMOVE_FORBIDDEN));
    }

    @Override
    public List<MoneyManipulationDTO> getAll(Authentication authentication) {
        return moneyManipulationRepository.getAllByUserEmail(authentication.getName()).stream()
                .map(moneyManipulationMapper::moneyManipulationToMoneyManipulationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MoneyManipulationDTO> getByType(Authentication authentication, MoneyManipulationTypeEnum moneyManipulationType) {
        return moneyManipulationRepository.getAllByUserEmailAndType(authentication.getName(), moneyManipulationType).stream()
                .map(moneyManipulationMapper::moneyManipulationToMoneyManipulationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MoneyManipulationDTO> getByColor(Authentication authentication, MoneyManipulationColorEnum moneyManipulationColor) {
        return moneyManipulationRepository.getAllByUserEmailAndColor(authentication.getName(), moneyManipulationColor).stream()
                .map(moneyManipulationMapper::moneyManipulationToMoneyManipulationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MoneyManipulationDTO> getByTypeAndColor(Authentication authentication, MoneyManipulationTypeEnum moneyManipulationType, MoneyManipulationColorEnum moneyManipulationColor) {
        return moneyManipulationRepository.getAllByUserEmailAndTypeAndColor(authentication.getName(), moneyManipulationType, moneyManipulationColor).stream()
                .map(moneyManipulationMapper::moneyManipulationToMoneyManipulationDTO)
                .collect(Collectors.toList());
    }
}
