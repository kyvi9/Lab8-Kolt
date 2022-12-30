package com.lab.application.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lab.application.client.CurrencyClient;
import com.lab.application.exeptions.AuthorityServiceException;
import com.lab.application.models.entities.Authority;
import com.lab.application.models.enums.AuthorityEnum;
import com.lab.application.repositories.AuthorityRepository;
import com.lab.application.services.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService
{
    private final AuthorityRepository authorityRepository;

    private final CurrencyClient currencyClient;

    @PostConstruct
    private void doOnInit() throws JsonProcessingException
    {
        Stream.of(AuthorityEnum.values())
                .map(AuthorityEnum::name)
                .filter(authority -> !authorityRepository.existsAuthorityByAuthority(authority))
                .map(Authority::new)
                .forEach(authorityRepository::save);
    }

    @Override
    public Authority getAuthority(AuthorityEnum authorityEnum) {
        return authorityRepository.getAuthorityByAuthority(authorityEnum.name())
                .orElseThrow(() -> new AuthorityServiceException(AuthorityServiceException.AUTHORITY_NOT_FOUND));
    }
}
