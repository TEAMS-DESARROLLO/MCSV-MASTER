package com.bussinesdomain.maestros.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.maestros.dto.RegionRequestDTO;
import com.bussinesdomain.maestros.dto.RegionResponseDTO;
import com.bussinesdomain.maestros.models.RegionEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IRegionMapper {

    
    RegionResponseDTO toGetDTO(RegionEntity entity);

    @InheritInverseConfiguration
    RegionEntity toEntity(RegionRequestDTO dto);

    List<RegionResponseDTO> listEntityToDTO(List<RegionEntity> lst);
}
