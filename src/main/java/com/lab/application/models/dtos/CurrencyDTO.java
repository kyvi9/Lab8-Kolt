package com.lab.application.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CurrencyDTO
{
    private String r030;
    private String txt;
    private String rate;
    private String cc;

    @JsonFormat(pattern="dd.MM.yyyy")
    private Date exchangedate;

}
