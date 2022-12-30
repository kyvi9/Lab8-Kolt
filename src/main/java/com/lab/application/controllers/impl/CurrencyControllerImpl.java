package com.lab.application.controllers.impl;

import com.lab.application.controllers.CurrencyController;
import com.lab.application.models.dtos.CurrencyDTO;
import com.lab.application.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class CurrencyControllerImpl implements CurrencyController
{
    private final CurrencyService currencyService;

    @Override
    public Collection<CurrencyDTO> getAll()
    {
        return currencyService.getAll();
    }
}
