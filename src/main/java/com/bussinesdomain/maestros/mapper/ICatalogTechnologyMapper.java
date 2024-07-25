package com.bussinesdomain.maestros.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.maestros.dto.CatalogTechnologyRequestDTO;
import com.bussinesdomain.maestros.dto.CatalogTechnologyResponseDTO;
import com.bussinesdomain.maestros.models.CatalogTechnologyEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICatalogTechnologyMapper {

    CatalogTechnologyResponseDTO toGetDTO(CatalogTechnologyEntity entity);


    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    @InheritInverseConfiguration
    CatalogTechnologyEntity toEntity(CatalogTechnologyRequestDTO dto);

    List<CatalogTechnologyResponseDTO> listEntityToDTO(List<CatalogTechnologyEntity> lst);
}
