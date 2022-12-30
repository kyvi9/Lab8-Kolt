package com.lab.application.mappers;

import com.lab.application.models.entities.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AuthorityMapper
{
    @Mappings({
            @Mapping(target = "authority", source = "authority.authority")
    })
    Authority authorityToAuthorityDTO(Authority authority);
}
