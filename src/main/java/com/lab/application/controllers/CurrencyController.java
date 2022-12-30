package com.lab.application.controllers;

import com.lab.application.models.dtos.CurrencyDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping("/currency")
public interface CurrencyController
{
    @GetMapping("/all")
    Collection<CurrencyDTO> getAll();
}
