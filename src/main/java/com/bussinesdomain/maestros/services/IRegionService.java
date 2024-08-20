package com.bussinesdomain.maestros.services;

import java.util.List;

import com.bussinesdomain.maestros.commons.IBaseInterfaceService;
import com.bussinesdomain.maestros.dto.RegionResponseDTO;
import com.bussinesdomain.maestros.models.RegionEntity;

public interface IRegionService extends IBaseInterfaceService<RegionEntity,Long> {

    List<RegionResponseDTO> regionsByIds(List<Long> ids);


}
