package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.FunctionalLeaderDTO;
import com.bussinesdomain.maestros.models.FunctionalLeaderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IFunctionalLeaderMapper {

    @Mapping(source = "idFunctionalLeader",target = "id")
    FunctionalLeaderDTO toGetDTO(FunctionalLeaderEntity entity);

    @InheritInverseConfiguration
    FunctionalLeaderEntity toEntity(FunctionalLeaderDTO dto);

    List<FunctionalLeaderDTO> listEntityToDTO(List<FunctionalLeaderEntity> lst);
}
