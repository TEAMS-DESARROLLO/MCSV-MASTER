package com.bussinesdomain.maestros.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.bussinesdomain.maestros.dto.UnitMeasureRequestDTO;
import com.bussinesdomain.maestros.dto.UnitMeasureResponseDTO;
import com.bussinesdomain.maestros.models.UnitMeasureEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IUnitMeasureMapper {

    UnitMeasureResponseDTO toGetDTO(UnitMeasureEntity entity);

    @InheritInverseConfiguration
    UnitMeasureEntity toEntity(UnitMeasureRequestDTO dto);

    List<UnitMeasureResponseDTO> listEntityToDTO(List<UnitMeasureEntity> lst);
}
