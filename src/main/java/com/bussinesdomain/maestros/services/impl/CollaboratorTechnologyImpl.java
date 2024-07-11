package com.bussinesdomain.maestros.services.impl;


import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.CollaboratorTechnologyEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ICollaboratorTechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollaboratorTechnologyImpl extends CRUDImpl<CollaboratorTechnologyEntity,Long> implements ICollaboratorTechnologyService {
    private final IGenericRepository<CollaboratorTechnologyEntity,Long> repository;


    @Override
    protected IGenericRepository<CollaboratorTechnologyEntity, Long> getRepo() {
        return repository;
    }

    @Override
    public CollaboratorTechnologyEntity update(CollaboratorTechnologyEntity entity,Long id){
        CollaboratorTechnologyEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        BeanUtils.copyProperties(entity,original);
        return super.update(entity,id);
    }
}
