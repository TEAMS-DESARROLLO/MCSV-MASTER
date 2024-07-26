package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.CollaboratorTechnologyDTO;
import com.bussinesdomain.maestros.dto.CollaboratorTechnologyResponseDTO;

import com.bussinesdomain.maestros.models.CollaboratorTechnologyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICollaboratorTechnologyMapper {
    @Mapping(target  = "idCollaborator", ignore = true)
    @Mapping(target  = "collaboratorNames", ignore = true)
    //@Mapping(target  = "idTechnology", ignore = true)
    //@Mapping(target  = "technologyName", ignore = true)
    CollaboratorTechnologyDTO toGetDTO(CollaboratorTechnologyEntity entity);


    @Mapping(target  = "idCollaborator", ignore = true)
    @Mapping(target  = "collaboratorNames", ignore = true)
    //@Mapping(target  = "idTechnology", ignore = true)
    //@Mapping(target  = "technologyName", ignore = true)
    CollaboratorTechnologyResponseDTO toGetResponseDTO(CollaboratorTechnologyEntity entity);





}
