package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.TechnologyDTO;
import com.bussinesdomain.maestros.models.TechnologyEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ITechnologyMapper {

    @Mapping(source = "idTechnology",target = "id")
    TechnologyDTO toGetDTO(TechnologyEntity entity);

    @InheritInverseConfiguration
    TechnologyEntity toEntity(TechnologyDTO dto);

    List<TechnologyDTO> listEntityToDTO(List<TechnologyEntity> lst);
}
