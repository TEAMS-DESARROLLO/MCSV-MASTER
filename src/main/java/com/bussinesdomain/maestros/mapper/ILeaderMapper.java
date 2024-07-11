package com.bussinesdomain.maestros.mapper;

import com.bussinesdomain.maestros.dto.LeaderDTO;
import com.bussinesdomain.maestros.models.LeaderEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ILeaderMapper {

    @Mapping(target  = "idCommunity", ignore = true)
    @Mapping(target  = "communityDescription", ignore = true)
    @Mapping(source = "idLeader",target = "id")
    LeaderDTO toGetDTO(LeaderEntity entity);

    @Mapping(target = "community",ignore = true)
    @InheritInverseConfiguration
    LeaderEntity toEntity(LeaderDTO dto);

    List<LeaderDTO> listEntityToDTO(List<LeaderEntity> lst);
}
