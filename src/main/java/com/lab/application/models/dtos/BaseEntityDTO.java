package com.lab.application.models.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BaseEntityDTO
{
    private String entityUUID;
    private Date createDate;
}
