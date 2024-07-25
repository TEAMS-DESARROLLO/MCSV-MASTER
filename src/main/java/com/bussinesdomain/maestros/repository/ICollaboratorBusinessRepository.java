package com.bussinesdomain.maestros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.models.CollaboratorEntity;

public interface ICollaboratorBusinessRepository extends JpaRepository<CollaboratorEntity,Long>{

    @Query("select r from CollaboratorEntity r where idCollaborator = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Optional<CollaboratorEntity> findActiveById(Long id);

    @Query("select count(*) from CollaboratorEntity where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Long countActive();

    @Query("select case when count(r) > 0 then true else false end from CollaboratorEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean existsActiveById(Long id);
    
    @Query("select r from CollaboratorEntity r where registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    List<CollaboratorEntity> findAllActive();


    @Query("select case when count(r) > 0 then true else false end from CollaboratorEntity r where r.functionalLeader.idFunctionalLeader = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean underFunctionalLeader(Long idFunctionalLeader);
    
    @Query("select case when count(r) > 0 then true else false end from CollaboratorEntity r where r.leader.idLeader = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean underLeader(Long idLeader);

    
    @Query("select case when count(r) > 0 then true else false end from CollaboratorEntity r where r.region.idRegion = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean underRegion(Long idRegion);

    @Query("select case when count(r) > 0 then true else false end from CollaboratorEntity r where r.rol.idRol = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean underRol(Long idRol);


    @Query("select case when count(r) > 0 then true else false end from CollaboratorEntity r where r.statusCollaborator.idStatusCollaborator = ?1 and registrationStatus='"+RegistrationStatus.ACTIVE+"' ")
    Boolean underStatusCollaborator(Long idStatusCollaborator);
    


}