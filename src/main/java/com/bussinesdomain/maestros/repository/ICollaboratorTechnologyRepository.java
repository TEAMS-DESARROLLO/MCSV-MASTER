package com.bussinesdomain.maestros.repository;

import com.bussinesdomain.maestros.models.CollaboratorTechnologyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICollaboratorTechnologyRepository extends IGenericRepository<CollaboratorTechnologyEntity,Long> {

}
