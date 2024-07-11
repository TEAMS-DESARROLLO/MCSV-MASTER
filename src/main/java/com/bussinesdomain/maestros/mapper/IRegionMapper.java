package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.RegionDTO;
import com.bussinesdomain.maestros.models.RegionEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IRegionMapper {

    @Mapping(source = "idRegion",target = "id")
    RegionDTO toGetDTO(RegionEntity entity);

    @InheritInverseConfiguration
    RegionEntity toEntity(RegionDTO dto);

    List<RegionDTO> listEntityToDTO(List<RegionEntity> lst);
}
