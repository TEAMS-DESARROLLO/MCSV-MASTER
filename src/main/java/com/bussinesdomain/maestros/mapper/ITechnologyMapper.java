package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.TechnologyDTO;
import com.bussinesdomain.maestros.dto.TechnologyRequestDTO;
import com.bussinesdomain.maestros.dto.TechnologyResponseDTO;
import com.bussinesdomain.maestros.models.TechnologyEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ITechnologyMapper {
    

    
    @Mapping(target  = "idSubpractica", ignore = true)
    @Mapping(target  = "descriptionSubpractica", ignore = true)
    TechnologyDTO toGetDTO(TechnologyEntity entity);

    @Mapping(target  = "idSubpractica", ignore = true)
    @Mapping(target  = "descriptionSubpractica", ignore = true)
    TechnologyResponseDTO toGetResponseDTO(TechnologyEntity entity);

    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    @InheritInverseConfiguration
    TechnologyEntity toEntity(TechnologyDTO dto);

    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)

    @InheritInverseConfiguration
    TechnologyEntity toEntity(TechnologyRequestDTO dto);

    List<TechnologyDTO> listEntityToDTO(List<TechnologyEntity> lst);
    List<TechnologyResponseDTO> listEntityToResponseDTO(List<TechnologyEntity> lst);
}
