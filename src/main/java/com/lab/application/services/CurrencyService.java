package com.lab.application.services;

import com.lab.application.models.dtos.CurrencyDTO;

import java.util.Collection;

public interface CurrencyService
{
    Collection<CurrencyDTO> getAll();
}
