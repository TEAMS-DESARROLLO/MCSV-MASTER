package com.bussinesdomain.maestros.services;

import java.util.List;

import com.bussinesdomain.maestros.commons.IBaseInterfaceService;
import com.bussinesdomain.maestros.dto.CollaboratorResponseDTO;
import com.bussinesdomain.maestros.models.CollaboratorEntity;

public interface ICollaboratorService extends IBaseInterfaceService<CollaboratorEntity,Long> {

    List<CollaboratorResponseDTO> collaboratorsByIds(List<Long> ids);

    CollaboratorResponseDTO collaboratorByIdquery(Long id);
}
