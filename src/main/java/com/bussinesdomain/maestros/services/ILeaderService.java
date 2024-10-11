package com.bussinesdomain.maestros.services;

import java.util.List;

import com.bussinesdomain.maestros.commons.IBaseInterfaceService;
import com.bussinesdomain.maestros.dto.LeaderResponseDTO;
import com.bussinesdomain.maestros.models.LeaderEntity;

public interface ILeaderService extends IBaseInterfaceService<LeaderEntity,Long> {

    List<LeaderResponseDTO> leadersByIds(List<Long> ids);

    LeaderEntity getLeaderEntityByIdPractice(Long idPractice);
    
}
