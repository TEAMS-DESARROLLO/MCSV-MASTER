package com.bussinesdomain.maestros.mapper;


import com.bussinesdomain.maestros.dto.CommunityDTO;
import com.bussinesdomain.maestros.dto.CommunityRequestDTO;
import com.bussinesdomain.maestros.dto.CommunityResponseDTO;
import com.bussinesdomain.maestros.models.CommunityEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICommunityMapper {

    CommunityDTO toGetDTO(CommunityEntity entity);

    CommunityResponseDTO toGetResponseDTO(CommunityEntity entity);

    @Mapping(target  = "createdAt", ignore = true)
    @Mapping(target  = "updatedAt", ignore = true)
    @InheritInverseConfiguration
    CommunityEntity toEntity(CommunityDTO dto);

    @Mapping(target  = "createdAt", ignore = true)
    @Mapping(target  = "updatedAt", ignore = true)
    CommunityEntity toEntity(CommunityRequestDTO dto);

    List<CommunityDTO> listEntityToDTO(List<CommunityEntity> list);
    List<CommunityResponseDTO> listEntityToResponseDTO(List<CommunityEntity> list);
}
