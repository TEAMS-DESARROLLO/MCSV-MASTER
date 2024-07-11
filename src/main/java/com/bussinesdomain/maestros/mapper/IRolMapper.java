package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.RolDTO;
import com.bussinesdomain.maestros.models.RolEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IRolMapper {

    @Mapping(source = "idRol",target = "id")
    RolDTO toGetDTO(RolEntity entity);

    @InheritInverseConfiguration
    RolEntity toEntity(RolDTO dto);

    List<RolDTO> listEntityToDTO(List<RolEntity> lst);
}
