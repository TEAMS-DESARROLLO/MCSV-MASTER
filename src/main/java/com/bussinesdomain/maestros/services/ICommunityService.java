package com.bussinesdomain.maestros.services;

import com.bussinesdomain.maestros.commons.IBaseInterfaceService;
import com.bussinesdomain.maestros.models.CommunityEntity;

public interface ICommunityService extends IBaseInterfaceService<CommunityEntity,Long> {

    CommunityEntity update(CommunityEntity entity, Long id);
}
