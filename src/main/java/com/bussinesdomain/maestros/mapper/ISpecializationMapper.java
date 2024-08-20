package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.SpecializationDTO;
import com.bussinesdomain.maestros.dto.SpecializationRequestDTO;
import com.bussinesdomain.maestros.dto.SpecializationResponseDTO;
import com.bussinesdomain.maestros.models.SpecializationEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ISpecializationMapper {

    @Mapping(target  = "idPractice", ignore = true)
    @Mapping(target  = "practiceDescription", ignore = true)
    SpecializationDTO toGetDTO(SpecializationEntity entity);

    @Mapping(target  = "idPractice", ignore = true)
    @Mapping(target  = "practiceDescription", ignore = true)
    SpecializationResponseDTO toGetResponseDTO(SpecializationEntity entity);

    @Mapping(target = "practiceEntity",ignore = true)
    
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    @InheritInverseConfiguration
    SpecializationEntity toEntity(SpecializationDTO dto);

    @Mapping(target = "practiceEntity",ignore = true)
    
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    @InheritInverseConfiguration
    SpecializationEntity toEntity(SpecializationRequestDTO dto);

    List<SpecializationDTO> listEntityToDTO(List<SpecializationEntity> lst);

    List<SpecializationResponseDTO> listEntityToResponseDTO(List<SpecializationEntity> lst);
}
