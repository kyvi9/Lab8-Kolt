package com.lab.application.repositories;

import com.lab.application.models.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>
{
    Optional<Authority> getAuthorityByAuthority(String authority);

    Boolean existsAuthorityByAuthority(String authority);
}
