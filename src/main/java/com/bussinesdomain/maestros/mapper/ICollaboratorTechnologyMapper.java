package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.CollaboratorDTO;
import com.bussinesdomain.maestros.dto.CollaboratorTechnologyDTO;
import com.bussinesdomain.maestros.models.CollaboratorEntity;
import com.bussinesdomain.maestros.models.CollaboratorTechnologyEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICollaboratorTechnologyMapper {
    @Mapping(target  = "idCollaborator", ignore = true)
    @Mapping(target  = "collaboratorNames", ignore = true)
    @Mapping(target  = "idTechnology", ignore = true)
    @Mapping(target  = "technologyName", ignore = true)
    @Mapping(source = "idCollaboratorTechnology",target = "id")
    CollaboratorTechnologyDTO toGetDTO(CollaboratorTechnologyEntity entity);

    @Mapping(target = "collaborator", ignore = true)
    @Mapping(target = "technology",ignore = true)
    @InheritInverseConfiguration
    CollaboratorTechnologyEntity toEntity(CollaboratorTechnologyDTO dto);

    List<CollaboratorTechnologyDTO> listEntityToDTO(List<CollaboratorTechnologyEntity> lst);
}
