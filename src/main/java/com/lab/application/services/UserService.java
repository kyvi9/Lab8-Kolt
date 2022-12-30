package com.lab.application.services;

import com.lab.application.models.dtos.UserDTO;
import com.lab.application.models.entities.MoneyManipulation;
import com.lab.application.models.entities.User;
import lombok.NonNull;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;

public interface UserService
{
    void register(@NonNull UserDTO userDTO);
    String authentication(@NonNull UserDTO userDTO);

    User getUserByAuthentication(@NonNull Authentication authentication);

    User withdrawMoney(@NonNull Authentication authentication, @NonNull BigDecimal amount);

    User addMoney(@NonNull Authentication authentication, @NonNull BigDecimal amount);

    User removeMoneyManipulation(@NonNull MoneyManipulation moneyManipulation);

    UserDTO getMyData(@NonNull Authentication authentication);
}
