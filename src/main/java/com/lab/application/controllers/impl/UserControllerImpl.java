package com.lab.application.controllers.impl;

import com.lab.application.controllers.UserController;
import com.lab.application.models.dtos.UserDTO;
import com.lab.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController
{
    @Value("${spring.security.jwt.name}")
    private String JWTName;

    private final UserService userService;

    @Override
    public void registration(UserDTO userDTO)
    {
        userService.register(userDTO);
    }

    @Override
    public Map<String, String> authentication(UserDTO userDTO) {
        return Map.of(JWTName, userService.authentication(userDTO));
    }

    @Override
    public UserDTO getMyData(Authentication authentication)
    {
        return userService.getMyData(authentication);
    }


}
