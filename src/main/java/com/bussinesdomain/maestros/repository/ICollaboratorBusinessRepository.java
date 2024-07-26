package com.bussinesdomain.maestros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bussinesdomain.maestros.models.CollaboratorEntity;

public interface ICollaboratorBusinessRepository extends JpaRepository<CollaboratorEntity,Long>{

    @Query("select r from CollaboratorEntity r where r.functionalLeader.idFunctionalLeader = :idFunctionalLeader  ")
    List<CollaboratorEntity> underFunctionalLeader(@Param("idFunctionalLeader") Long idFunctionalLeader);
    
    @Query("select r from CollaboratorEntity r where r.leader.idLeader = :idLeader  ")
    List<CollaboratorEntity> underLeader(@Param("idLeader") Long idLeader);

    
    @Query("select r from CollaboratorEntity r where r.region.idRegion = :idRegion  ")
    List<CollaboratorEntity> underRegion(@Param("idRegion") Long idRegion);

    @Query("select r from CollaboratorEntity r where r.rol.idRol = :idRol  ")
    List<CollaboratorEntity> underRol(@Param("idRol") Long idRol);


    @Query("select r from CollaboratorEntity r where r.statusCollaborator.idStatusCollaborator = :idStatusCollaborator  ")
    List<CollaboratorEntity> underStatusCollaborator(@Param("idStatusCollaborator") Long idStatusCollaborator);
    


}