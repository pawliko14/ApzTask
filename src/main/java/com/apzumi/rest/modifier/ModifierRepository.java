package com.apzumi.rest.modifier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface ModifierRepository extends JpaRepository<Modifier, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO MODIFIER SELECT ID,'TRUE' FROM POST ", nativeQuery = true)
    void findAllData();


    @Modifying
    @Transactional
    @Query(value = "UPDATE MODIFIER m set m.ENABLE_TO_UPDATE = FALSE WHERE m.ID = ?", nativeQuery = true)
    void updateEnableToModifiy(long id);


}
