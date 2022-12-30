package com.lab.application.repositories;

import com.lab.application.models.entities.MoneyManipulation;
import com.lab.application.models.enums.MoneyManipulationColorEnum;
import com.lab.application.models.enums.MoneyManipulationTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoneyManipulationRepository extends JpaRepository<MoneyManipulation, Long>
{
    Optional<MoneyManipulation> getMoneyManipulationByEntityUUID(String entityUUID);
    Optional<MoneyManipulation> getMoneyManipulationByEntityUUIDAndUserEmail(String entityUUID, String email);

    List<MoneyManipulation> getAllByUserEmail(String email);
    List<MoneyManipulation> getAllByUserEmailAndType(String email, MoneyManipulationTypeEnum type);
    List<MoneyManipulation> getAllByUserEmailAndColor(String email, MoneyManipulationColorEnum color);
    List<MoneyManipulation> getAllByUserEmailAndTypeAndColor(String email,
                                                             MoneyManipulationTypeEnum type,
                                                             MoneyManipulationColorEnum color);
}
