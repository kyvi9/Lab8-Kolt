package com.lab.application.services.impl;

import com.lab.application.client.CurrencyClient;
import com.lab.application.models.dtos.CurrencyDTO;
import com.lab.application.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService
{
    private final CurrencyClient currencyClient;

    @Override
    public Collection<CurrencyDTO> getAll()
    {
        return Stream.of(currencyClient.get())
                .collect(Collectors.toList());
    }
}
