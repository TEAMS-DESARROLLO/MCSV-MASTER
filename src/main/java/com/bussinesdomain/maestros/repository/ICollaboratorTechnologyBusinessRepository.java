package com.bussinesdomain.maestros.repository;

import com.bussinesdomain.maestros.models.CollaboratorTechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICollaboratorTechnologyBusinessRepository extends JpaRepository<CollaboratorTechnologyEntity,Long>{

    // @Query("SELECT ct FROM CollaboratorTechnologyEntity ct WHERE ct.collaborator.idCollaborator = :idCollaborator")
    // List<CollaboratorTechnologyEntity> getByCollaboratorId(@Param("idCollaborator")Long idCollaborator);

    // @Query("SELECT 1 FROM CollaboratorTechnologyEntity ct WHERE ct.collaborator.idCollaborator = :idCollaborator and ct.technology.idTechnology = :idTechnology")
    // Boolean existsTechnologyInCollaborator(@Param("idCollaborator")Long idCollaborator,@Param("idTechnology")Long idTechnology);

    // @Query("SELECT ct FROM CollaboratorTechnologyEntity ct WHERE ct.collaborator.idCollaborator = :idCollaborator and ct.technology.idTechnology = :idTechnology")
    // CollaboratorTechnologyEntity getTechnologyInCollaborator(@Param("idCollaborator")Long idCollaborator,@Param("idTechnology")Long idTechnology);
}
