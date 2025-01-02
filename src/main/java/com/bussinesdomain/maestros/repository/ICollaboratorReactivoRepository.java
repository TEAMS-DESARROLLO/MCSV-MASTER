package com.bussinesdomain.maestros.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.bussinesdomain.maestros.models.CollaboratorEntity;

public interface ICollaboratorReactivoRepository extends R2dbcRepository<CollaboratorEntity,Long> {
    


}
