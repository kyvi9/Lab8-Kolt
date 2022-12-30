package com.lab.application.client;

import com.lab.application.models.dtos.CurrencyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "amo-crm", url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json")
public interface CurrencyClient
{
    @GetMapping()
    CurrencyDTO[] get();
}
