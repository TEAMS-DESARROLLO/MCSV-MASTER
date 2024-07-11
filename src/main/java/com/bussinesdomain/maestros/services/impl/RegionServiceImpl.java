package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.RegionEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.IRegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl extends CRUDImpl<RegionEntity,Long> implements IRegionService {
    private final IGenericRepository<RegionEntity,Long> repository;


    @Override
    protected IGenericRepository<RegionEntity, Long> getRepo() {
        return repository;
    }
    @Override
    public RegionEntity update(RegionEntity entity, Long id){
        RegionEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        BeanUtils.copyProperties(entity,original);
        return super.update(entity,id);
    }
}

