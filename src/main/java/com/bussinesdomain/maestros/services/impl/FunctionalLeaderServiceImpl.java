package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.FunctionalLeaderEntity;

import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.IFunctionalLeaderService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FunctionalLeaderServiceImpl extends CRUDImpl<FunctionalLeaderEntity,Long> implements IFunctionalLeaderService {
    private final IGenericRepository<FunctionalLeaderEntity,Long> repository;



    @Override
    protected IGenericRepository<FunctionalLeaderEntity, Long> getRepo() {
        return repository;
    }

    @Override
    public FunctionalLeaderEntity update(FunctionalLeaderEntity entity,Long id){
        FunctionalLeaderEntity original = this.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idFunctionalLeader","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }


}
