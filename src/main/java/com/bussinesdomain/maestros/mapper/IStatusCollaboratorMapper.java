package com.bussinesdomain.maestros.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.maestros.dto.StatusCollaboratorRequestDTO;
import com.bussinesdomain.maestros.dto.StatusCollaboratorResponseDTO;
import com.bussinesdomain.maestros.models.StatusCollaboratorEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IStatusCollaboratorMapper {
    StatusCollaboratorResponseDTO toGetDTO(StatusCollaboratorEntity entity);


    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    @InheritInverseConfiguration
    StatusCollaboratorEntity toEntity(StatusCollaboratorRequestDTO dto);

    List<StatusCollaboratorResponseDTO> listEntityToDTO(List<StatusCollaboratorEntity> lst);
}
