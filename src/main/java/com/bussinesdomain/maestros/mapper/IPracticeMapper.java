package com.bussinesdomain.maestros.mapper;


import com.bussinesdomain.maestros.dto.PracticeDTO;
import com.bussinesdomain.maestros.dto.PracticeRequestDTO;
import com.bussinesdomain.maestros.dto.PracticeResponseDTO;
import com.bussinesdomain.maestros.models.PracticeEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IPracticeMapper {

    @Mapping(target  = "idRegion", ignore = true)
    @Mapping(target  = "regionDescription", ignore = true)
    PracticeDTO toGetDTO(PracticeEntity entity);

    @Mapping(target  = "idRegion", ignore = true)
    @Mapping(target  = "regionDescription", ignore = true)
    PracticeResponseDTO toGetResponseDTO(PracticeEntity entity);

    @Mapping(target  = "createdAt", ignore = true)
    @Mapping(target  = "updatedAt", ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    @InheritInverseConfiguration
    PracticeEntity toEntity(PracticeDTO dto);

    @Mapping(target  = "createdAt", ignore = true)
    @Mapping(target  = "updatedAt", ignore = true)
    @Mapping(target = "registrationStatus",ignore = true)
    PracticeEntity toEntity(PracticeRequestDTO dto);

    List<PracticeDTO> listEntityToDTO(List<PracticeEntity> list);
    List<PracticeResponseDTO> listEntityToResponseDTO(List<PracticeEntity> list);
}
