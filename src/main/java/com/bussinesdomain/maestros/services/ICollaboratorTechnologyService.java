package com.bussinesdomain.maestros.services;

import com.bussinesdomain.maestros.commons.IBaseInterfaceService;
import com.bussinesdomain.maestros.models.CollaboratorTechnologyEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICollaboratorTechnologyService extends IBaseInterfaceService<CollaboratorTechnologyEntity,Long> {
    CollaboratorTechnologyEntity getTechnologyInCollaborator(Long idCollaborator,Long idTechnology);
    List<CollaboratorTechnologyEntity> getByCollaboratorId(Long idCollaborator);

}
