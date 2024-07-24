package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.models.CollaboratorEntity;

public interface ICollaboratorRepository extends IGenericRepository<CollaboratorEntity,Long> {
    @Query("select r from CollaboratorEntity r where idCollaborator = ?1 and registrationStatus='A' ")
    Optional<CollaboratorEntity> findActiveById(Long id);

    @Override
    @Query("select count(*) from CollaboratorEntity where registrationStatus='A' ")
    Long countActive();
    @Override
    @Query("select case when count(r) > 0 then true else false end from CollaboratorEntity r where registrationStatus='A' ")
    Boolean existsActiveById(Long id);
    @Override
    @Query("select r from CollaboratorEntity r where registrationStatus='A' ")
    List<CollaboratorEntity> findAllActive(); 

    @Modifying
    @Query("update CollaboratorEntity r set registrationStatus='I' where idCollaborator = ?1")
    @Override
    void logicalDeleteById(Long id);
}
