package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.models.RolEntity;

public interface IRolBusinessRepository extends JpaRepository<RolEntity,Long>{

    @Query("select r from RolEntity r where idRol = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Optional<RolEntity> findActiveById(Long id);

    @Query("select count(*) from RolEntity where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Long countActive();

    @Query("select case when count(r) > 0 then true else false end from RolEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean existsActiveById(Long id);
    
    @Query("select r from RolEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    List<RolEntity> findAllActive();
}

