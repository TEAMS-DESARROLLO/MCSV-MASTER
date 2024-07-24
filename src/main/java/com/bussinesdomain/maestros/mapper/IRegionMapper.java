package com.bussinesdomain.maestros.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.maestros.dto.RegionRequestDTO;
import com.bussinesdomain.maestros.dto.RegionResponseDTO;
import com.bussinesdomain.maestros.models.RegionEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IRegionMapper {

    
    RegionResponseDTO toGetDTO(RegionEntity entity);

    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    @InheritInverseConfiguration
    RegionEntity toEntity(RegionRequestDTO dto);

    List<RegionResponseDTO> listEntityToDTO(List<RegionEntity> lst);
}
