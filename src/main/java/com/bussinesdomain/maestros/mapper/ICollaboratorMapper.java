package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.CollaboratorDTO;
import com.bussinesdomain.maestros.dto.CollaboratorRequestDTO;
import com.bussinesdomain.maestros.dto.CollaboratorResponseDTO;
import com.bussinesdomain.maestros.models.CollaboratorEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICollaboratorMapper {
    @Mapping(target  = "idLeader", ignore = true)
    @Mapping(target  = "idRol", ignore = true)
    @Mapping(target  = "idRegion", ignore = true)
    @Mapping(target  = "idFunctionalLeader", ignore = true)
    CollaboratorDTO toGetDTO(CollaboratorEntity entity);

    @Mapping(target  = "idLeader", ignore = true)
    @Mapping(target  = "leaderNames", ignore = true)
    @Mapping(target  = "idRol", ignore = true)
    @Mapping(target  = "rolDescription", ignore = true)
    @Mapping(target  = "idRegion", ignore = true)
    @Mapping(target  = "regionDescription", ignore = true)
    @Mapping(target  = "idFunctionalLeader", ignore = true)
    CollaboratorResponseDTO toGetResponseDTO(CollaboratorEntity entity);

    @Mapping(target = "leader", ignore = true)
    @Mapping(target = "rol",ignore = true)
    @Mapping(target = "region",ignore = true)
    @Mapping(target = "functionalLeader",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    @InheritInverseConfiguration
    CollaboratorEntity toEntity(CollaboratorDTO dto);


    @Mapping(target = "leader", ignore = true)
    @Mapping(target = "rol",ignore = true)
    @Mapping(target = "region",ignore = true)
    @Mapping(target = "functionalLeader",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    @InheritInverseConfiguration
    CollaboratorEntity toEntity(CollaboratorRequestDTO dto);

    List<CollaboratorDTO> listEntityToDTO(List<CollaboratorEntity> lst);
    List<CollaboratorResponseDTO> listEntityToResponseDTO(List<CollaboratorEntity> lst);
}
