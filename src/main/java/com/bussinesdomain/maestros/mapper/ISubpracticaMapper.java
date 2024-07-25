package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.SubpracticaDTO;
import com.bussinesdomain.maestros.dto.SubpracticaRequestDTO;
import com.bussinesdomain.maestros.dto.SubpracticaResponseDTO;
import com.bussinesdomain.maestros.models.SubpracticaEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ISubpracticaMapper {

    @Mapping(target  = "idCommunity", ignore = true)
    @Mapping(target  = "communityDescription", ignore = true)
    SubpracticaDTO toGetDTO(SubpracticaEntity entity);

    @Mapping(target  = "idCommunity", ignore = true)
    @Mapping(target  = "communityDescription", ignore = true)
    SubpracticaResponseDTO toGetResponseDTO(SubpracticaEntity entity);

    @Mapping(target = "comunidadEntity",ignore = true)
    
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    @InheritInverseConfiguration
    SubpracticaEntity toEntity(SubpracticaDTO dto);

    @Mapping(target = "comunidadEntity",ignore = true)
    
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    @InheritInverseConfiguration
    SubpracticaEntity toEntity(SubpracticaRequestDTO dto);

    List<SubpracticaDTO> listEntityToDTO(List<SubpracticaEntity> lst);

    List<SubpracticaResponseDTO> listEntityToResponseDTO(List<SubpracticaEntity> lst);
}
