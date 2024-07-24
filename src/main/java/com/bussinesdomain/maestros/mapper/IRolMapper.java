package com.bussinesdomain.maestros.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.maestros.dto.RolRequestDTO;
import com.bussinesdomain.maestros.dto.RolResponseDTO;
import com.bussinesdomain.maestros.models.RolEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IRolMapper {

    
    RolResponseDTO toGetDTO(RolEntity entity);

    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    @InheritInverseConfiguration
    RolEntity toEntity(RolRequestDTO dto);

    List<RolResponseDTO> listEntityToDTO(List<RolEntity> lst);
}
