package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.models.StatusCollaboratorEntity;

public interface IStatusCollaboratorBusinessRepository extends JpaRepository<StatusCollaboratorEntity,Long>{

    @Query("select r from StatusCollaboratorEntity r where idStatusCollaborator = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Optional<StatusCollaboratorEntity> findActiveById(Long id);

    @Query("select count(*) from StatusCollaboratorEntity where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Long countActive();

    @Query("select case when count(r) > 0 then true else false end from StatusCollaboratorEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean existsActiveById(Long id);
    
    @Query("select r from StatusCollaboratorEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    List<StatusCollaboratorEntity> findAllActive();
}
