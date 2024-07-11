package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.UnitMeasureDTO;
import com.bussinesdomain.maestros.models.UnitMeasureEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IUnitMeasureMapper {

    UnitMeasureDTO toGetDTO(UnitMeasureEntity entity);

    @InheritInverseConfiguration
    UnitMeasureEntity toEntity(UnitMeasureDTO dto);

    List<UnitMeasureDTO> listEntityToDTO(List<UnitMeasureEntity> lst);
}
