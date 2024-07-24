package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.models.CollaboratorTechnologyEntity;


public interface ICollaboratorTechnologyRepository extends IGenericRepository<CollaboratorTechnologyEntity,Long> {

    @Query("select r from CollaboratorTechnologyEntity r where idCollaboratorTechnology = ?1 and registrationStatus='A' ")
    Optional<CollaboratorTechnologyEntity> findActiveById(Long id);

    @Override
    @Query("select count(*) from CollaboratorTechnologyEntity where registrationStatus='A' ")
    Long countActive();
    @Override
    @Query("select case when count(r) > 0 then true else false end from CollaboratorTechnologyEntity r where registrationStatus='A' ")
    Boolean existsActiveById(Long id);
    @Override
    @Query("select r from CollaboratorTechnologyEntity r where registrationStatus='A' ")
    List<CollaboratorTechnologyEntity> findAllActive(); 

    @Modifying
    @Query("update CollaboratorTechnologyEntity r set registrationStatus='I' where idCollaboratorTechnology = ?1")
    @Override
    void logicalDeleteById(Long id);
}
