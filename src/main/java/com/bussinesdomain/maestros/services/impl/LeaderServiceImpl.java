package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.LeaderEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ILeaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaderServiceImpl extends CRUDImpl<LeaderEntity,Long> implements ILeaderService {

    private final IGenericRepository<LeaderEntity,Long> repository;

    @Override
    public LeaderEntity update(LeaderEntity entity, Long id) {
        LeaderEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        BeanUtils.copyProperties(entity,original);
        return super.update(entity, id);
    }

    @Override
    protected IGenericRepository<LeaderEntity, Long> getRepo() {
        return repository;
    }
}
