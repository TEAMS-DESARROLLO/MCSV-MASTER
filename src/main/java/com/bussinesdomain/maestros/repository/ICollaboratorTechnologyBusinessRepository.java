package com.bussinesdomain.maestros.repository;


import com.bussinesdomain.maestros.models.CollaboratorTechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICollaboratorTechnologyBusinessRepository extends JpaRepository<CollaboratorTechnologyEntity,Long>{

    
    @Query("select r from CollaboratorTechnologyEntity r where r.collaborator.idCollaborator = :idCollaborator ")
    List<CollaboratorTechnologyEntity> underCollaborator(@Param("idCollaborator") Long idCollaborator);

    
    @Query("select r from CollaboratorTechnologyEntity r where r.catalogTechnology.idCatalogTechnology = :idCatalogTechnology ")
    List<CollaboratorTechnologyEntity> underCatalogTechnology(@Param("idCatalogTechnology") Long idCatalogTechnology);
}
