package com.lab.application.controllers;

import com.lab.application.models.dtos.MoneyManipulationDTO;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/money")
public interface MoneyManipulationController
{
    @PostMapping("/add/manipulation")
    MoneyManipulationDTO addManipulation(@RequestBody MoneyManipulationDTO moneyManipulationDTO,
                                         Authentication authentication);

    @DeleteMapping("/remove/manipulation/{manipulation_uuid}")
    void removeManipulation(@PathVariable("manipulation_uuid") String manipulationUUID, Authentication authentication);

    @GetMapping("/get/all/manipulations")
    List<MoneyManipulationDTO> getAllMoneyManipulation(Authentication authentication);

    @GetMapping("/get/manipulations/by/type/{type}")
    List<MoneyManipulationDTO> getMoneyManipulationByType(@PathVariable("type") String type,
                                                          Authentication authentication);

    @GetMapping("/get/manipulations/by/color/{color}")
    List<MoneyManipulationDTO> getMoneyManipulationByColor(@PathVariable("color") String color,
                                                           Authentication authentication);

    @GetMapping("/get/manipulations/by/type/{type}/and/color/{color}")
    List<MoneyManipulationDTO> getMoneyManipulationByTypeAndColor(@PathVariable("type") String type,
                                                                  @PathVariable("color") String color,
                                                                  Authentication authentication);
}
