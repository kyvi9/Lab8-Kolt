package com.lab.application.services.impl;

import com.lab.application.exeptions.MoneyManipulationServiceException;
import com.lab.application.exeptions.UserServiceException;
import com.lab.application.mappers.UserMapper;
import com.lab.application.models.dtos.UserDTO;
import com.lab.application.models.entities.MoneyManipulation;
import com.lab.application.models.entities.User;
import com.lab.application.models.enums.AuthorityEnum;
import com.lab.application.models.enums.MoneyManipulationTypeEnum;
import com.lab.application.repositories.UserRepository;
import com.lab.application.services.AuthorityService;
import com.lab.application.services.JWTService;
import com.lab.application.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorityService authorityService;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(UserDTO userDTO)
    {
        Optional.ofNullable(userRepository.existsUserByEmail(userDTO.getEmail()))
                .filter(isExists -> !isExists)
                .map(ignore -> userMapper.userDTOtoUser(userDTO))
                .map(user -> {
                    user.setAuthorities(Set.of(authorityService.getAuthority(AuthorityEnum.USER)));
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    return user;
                })
                .map(userRepository::save)
                .map(userMapper::userToUserDTO)
                .orElseThrow(() -> new UserServiceException(UserServiceException.EXISTS_USER_BY_EMAIL));
    }

    @Override
    public String authentication(UserDTO userDTO) {
        return userRepository.getUserByEmail(userDTO.getEmail())
                .filter(user -> passwordEncoder.matches(userDTO.getPassword(), user.getPassword()))
                .map(jwtService::generate)
                .orElseThrow(() -> new UserServiceException(UserServiceException.WRONG_EMAIL_OR_PASSWORD));
    }

    @Override
    public User getUserByAuthentication(Authentication authentication)
    {
        return userRepository.getUserByEmail(authentication.getName())
                .orElseThrow(() -> new UserServiceException(UserServiceException.WRONG_AUTHENTICATION_DATA));
    }

    @Override
    public User addMoney(Authentication authentication, BigDecimal amount)
    {
        return manipulateWithMoney(authentication, amount, MoneyManipulationTypeEnum.INCOME);
    }

    @Override
    public User withdrawMoney(Authentication authentication, BigDecimal amount)
    {
        return manipulateWithMoney(authentication, amount, MoneyManipulationTypeEnum.COSTS);
    }

    @Override
    public User removeMoneyManipulation(@NonNull MoneyManipulation moneyManipulation)
    {
        User user = switch (moneyManipulation.getType())
        {
            case INCOME -> manipulateWithMoney(moneyManipulation.getUser(), moneyManipulation.getMoneyAmount(), MoneyManipulationTypeEnum.COSTS);
            case COSTS -> manipulateWithMoney(moneyManipulation.getUser(), moneyManipulation.getMoneyAmount(), MoneyManipulationTypeEnum.INCOME);
            default -> throw new UserServiceException(MoneyManipulationServiceException.WRONG_MONEY_MANIPULATION_TYPE);
        };

        return userRepository.save(user);
    }

    @Override
    public UserDTO getMyData(Authentication authentication)
    {
        return userRepository.getUserByEmail(authentication.getName())
                .map(userMapper::userToUserDTO)
                .orElseThrow(() -> new UserServiceException(UserServiceException.WRONG_AUTHENTICATION_DATA));
    }

    private User manipulateWithMoney(Authentication authentication, BigDecimal amount, MoneyManipulationTypeEnum moneyManipulationType)
    {
        return manipulateWithMoney(getUserByAuthentication(authentication), amount, moneyManipulationType);
    }

    private User manipulateWithMoney(User user, BigDecimal amount, MoneyManipulationTypeEnum moneyManipulationType)
    {
        amount = amount.abs();

        BigDecimal userAmount = user.getMoneyAmount();

        user.setMoneyAmount(moneyManipulationType == MoneyManipulationTypeEnum.INCOME ? userAmount.add(amount) : userAmount.subtract(amount));

        return userRepository.save(user);
    }
}
