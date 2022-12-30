package com.lab.application.services;

import com.lab.application.models.entities.Authority;
import com.lab.application.models.enums.AuthorityEnum;
import lombok.NonNull;

public interface AuthorityService
{
    Authority getAuthority(@NonNull AuthorityEnum authorityEnum);
}
