package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.FunctionalLeaderEntity;
import com.bussinesdomain.maestros.models.LeaderEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.IFunctionalLeaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuncionalLeaderServiceImpl extends CRUDImpl<FunctionalLeaderEntity,Long> implements IFunctionalLeaderService {
    private final IGenericRepository<FunctionalLeaderEntity,Long> repository;

    @Override
    public FunctionalLeaderEntity update(FunctionalLeaderEntity entity,Long id){
        FunctionalLeaderEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idFunctionalLeader"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(entity,id);
    }

    @Override
    protected IGenericRepository<FunctionalLeaderEntity, Long> getRepo() {
        return repository;
    }
}
