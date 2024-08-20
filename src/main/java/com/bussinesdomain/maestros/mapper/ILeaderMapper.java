package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.LeaderDTO;
import com.bussinesdomain.maestros.dto.LeaderRequestDTO;
import com.bussinesdomain.maestros.dto.LeaderResponseDTO;
import com.bussinesdomain.maestros.models.LeaderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ILeaderMapper {

    @Mapping(target  = "idPractice", ignore = true)
    @Mapping(target  = "practiceDescription", ignore = true)
    LeaderDTO toGetDTO(LeaderEntity entity);

    @Mapping(target  = "idPractice", ignore = true)
    @Mapping(target  = "practiceDescription", ignore = true)
    LeaderResponseDTO toGetResponseDTO(LeaderEntity entity);

    @Mapping(target = "practice",ignore = true)
    
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    @InheritInverseConfiguration
    LeaderEntity toEntity(LeaderDTO dto);

    @Mapping(target = "practice",ignore = true)
    
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    @InheritInverseConfiguration
    LeaderEntity toEntity(LeaderRequestDTO dto);

    List<LeaderDTO> listEntityToDTO(List<LeaderEntity> lst);

    List<LeaderResponseDTO> listEntityToResponseDTO(List<LeaderEntity> lst);
}
