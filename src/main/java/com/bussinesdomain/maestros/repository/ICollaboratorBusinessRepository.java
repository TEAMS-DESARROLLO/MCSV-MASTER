package com.bussinesdomain.maestros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bussinesdomain.maestros.dto.CollaboratorResponseDTO;
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

    // @Query("select r from CollaboratorEntity r where r.idCollaborator in :ids  ")
    // List<CollaboratorEntity> collaboratorsByIds(@Param("ids") List<Long> ids);    

    @Query( value =
        "SELECT new com.bussinesdomain.maestros.dto.CollaboratorResponseDTO(a.idCollaborator," +
                " a.lastName," +
                " a.names," +
                " a.email," +
                " a.state, " +
                " l.idLeader," +
                " l.names as leaderNames," +
                " r.idRol," +
                " r.description as rolDescription," +
                " rg.idRegion," +
                " rg.description as regionDescription," +
                " fl.idFunctionalLeader," +
                " fl.names as functionalLeaderNames," +
                " sc.idStatusCollaborator ," +
                " sc.descriptionStatusCollaborator ," +
                " ce.idPractice as idPractice," +
                " ce.description as communityDescription) "  +
                " FROM CollaboratorEntity a " +
                " left join LeaderEntity l on a.leader=l " +
                " left join RolEntity r on a.rol=r " +
                " left join RegionEntity rg on a.region=rg " +
                " left join FunctionalLeaderEntity fl on a.functionalLeader=fl " +
                " left join StatusCollaboratorEntity sc on a.statusCollaborator = sc " +
                " left join PracticeEntity ce on a.practice = ce " +
                " where a.idCollaborator in :ids "
    )
    List<CollaboratorResponseDTO> collaboratorsByIds(@Param("ids") List<Long> ids);

    @Query( value =
        "SELECT new com.bussinesdomain.maestros.dto.CollaboratorResponseDTO(a.idCollaborator," +
                " a.lastName," +
                " a.names," +
                " a.email," +
                " a.state, " +
                " l.idLeader," +
                " l.names as leaderNames," +
                " r.idRol," +
                " r.description as rolDescription," +
                " rg.idRegion," +
                " rg.description as regionDescription," +
                " fl.idFunctionalLeader," +
                " fl.names as functionalLeaderNames," +
                " sc.idStatusCollaborator ," +
                " sc.descriptionStatusCollaborator ," +
                " ce.idPractice as idPractice," +
                " ce.description as communityDescription) "  +
                " FROM CollaboratorEntity a " +
                " left join LeaderEntity l on a.leader=l " +
                " left join RolEntity r on a.rol=r " +
                " left join RegionEntity rg on a.region=rg " +
                " left join FunctionalLeaderEntity fl on a.functionalLeader=fl " +
                " left join StatusCollaboratorEntity sc on a.statusCollaborator = sc " +
                " left join PracticeEntity ce on a.practice = ce " +
                " where a.idCollaborator in :id "
    )
    CollaboratorResponseDTO collaboratorByIdquery(@Param("id") Long id);    

}