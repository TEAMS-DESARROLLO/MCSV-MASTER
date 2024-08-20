package com.bussinesdomain.maestros.services;

import java.util.List;

import com.bussinesdomain.maestros.commons.IBaseInterfaceService;
import com.bussinesdomain.maestros.dto.PracticeResponseDTO;
import com.bussinesdomain.maestros.models.PracticeEntity;

public interface IPracticeService extends IBaseInterfaceService<PracticeEntity,Long> {

    PracticeEntity update(PracticeEntity entity, Long id);

    List<PracticeResponseDTO> communitiesByIds(List<Long> ids);
}
