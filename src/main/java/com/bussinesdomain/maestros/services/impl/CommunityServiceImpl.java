package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.CollaboratorEntity;
import com.bussinesdomain.maestros.models.CommunityEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ICommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl extends CRUDImpl<CommunityEntity,Long> implements ICommunityService {

    private final IGenericRepository<CommunityEntity, Long> repository;

    @Override
    public CommunityEntity update(CommunityEntity entity, Long id) {
        CommunityEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"createdAt","updatedAt","idCommunity"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }

    @Override
    protected IGenericRepository<CommunityEntity, Long> getRepo() {
        return repository;
    }
}
