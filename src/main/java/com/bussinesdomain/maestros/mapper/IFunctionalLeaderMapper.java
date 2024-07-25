package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.FunctionalLeaderDTO;
import com.bussinesdomain.maestros.dto.FunctionalLeaderRequestDTO;
import com.bussinesdomain.maestros.dto.FunctionalLeaderResponseDTO;
import com.bussinesdomain.maestros.models.FunctionalLeaderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IFunctionalLeaderMapper {

    FunctionalLeaderDTO toGetDTO(FunctionalLeaderEntity entity);


    FunctionalLeaderResponseDTO toGetResponseDTO(FunctionalLeaderEntity entity);

    @InheritInverseConfiguration
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    FunctionalLeaderEntity toEntity(FunctionalLeaderDTO dto);

    @InheritInverseConfiguration
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    FunctionalLeaderEntity toEntity(FunctionalLeaderRequestDTO dto);

    List<FunctionalLeaderDTO> listEntityToDTO(List<FunctionalLeaderEntity> lst);

    List<FunctionalLeaderResponseDTO> listEntityToResponseDTO(List<FunctionalLeaderEntity> lst);
}
