package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.LeaderEntity;
import com.bussinesdomain.maestros.models.RegionEntity;
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
    public LeaderEntity create(LeaderEntity entidad) {
        return super.create(entidad);
    }

    @Override
    public LeaderEntity update(LeaderEntity entity, Long id) {
        LeaderEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idLeader","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original, id);
    }

    @Override
    protected IGenericRepository<LeaderEntity, Long> getRepo() {
        return repository;
    }
}
