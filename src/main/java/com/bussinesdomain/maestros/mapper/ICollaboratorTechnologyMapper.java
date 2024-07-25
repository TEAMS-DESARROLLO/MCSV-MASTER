package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.CollaboratorTechnologyDTO;
import com.bussinesdomain.maestros.dto.CollaboratorTechnologyRequestDTO;
import com.bussinesdomain.maestros.dto.CollaboratorTechnologyResponseDTO;
import com.bussinesdomain.maestros.dto.CommunityDTO;
import com.bussinesdomain.maestros.dto.CommunityRequestDTO;
import com.bussinesdomain.maestros.models.CollaboratorTechnologyEntity;
import com.bussinesdomain.maestros.models.CommunityEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;



@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICollaboratorTechnologyMapper {
    @Mapping(target  = "idCollaborator", ignore = true)
    @Mapping(target  = "collaboratorNames", ignore = true)
    @Mapping(target  = "idCatalogTechnology", ignore = true)
    @Mapping(target  = "descriptionCatalogTechnology", ignore = true)
    CollaboratorTechnologyDTO toGetDTO(CollaboratorTechnologyEntity entity);

    @Mapping(target  = "createdAt", ignore = true)
    @Mapping(target  = "updatedAt", ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    CollaboratorTechnologyEntity toEntity(CollaboratorTechnologyRequestDTO dto);



    @Mapping(target  = "idCollaborator", ignore = true)
    @Mapping(target  = "collaboratorNames", ignore = true)
    @Mapping(target  = "idCatalogTechnology", ignore = true)
    @Mapping(target  = "descriptionCatalogTechnology", ignore = true)
    CollaboratorTechnologyResponseDTO toGetResponseDTO(CollaboratorTechnologyEntity entity);





}
