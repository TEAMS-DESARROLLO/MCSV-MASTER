package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.CollaboratorTechnologyEntity;
import com.bussinesdomain.maestros.repository.ICollaboratorTechnologyBusinessRepository;
import com.bussinesdomain.maestros.repository.IGenericRepository;

import com.bussinesdomain.maestros.services.ICollaboratorTechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollaboratorTechnologyServiceImpl extends CRUDImpl<CollaboratorTechnologyEntity,Long> implements ICollaboratorTechnologyService {
    
    private final IGenericRepository<CollaboratorTechnologyEntity,Long> repository;


    @Override
    protected IGenericRepository<CollaboratorTechnologyEntity, Long> getRepo() {
        return repository;
    }



    @Override
    public CollaboratorTechnologyEntity update(CollaboratorTechnologyEntity entity,Long id){
        CollaboratorTechnologyEntity original = this.readById(id).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);;
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        BeanUtils.copyProperties(entity,original);
        return super.update(entity,id);
    }

    @Override
    public CollaboratorTechnologyEntity getTechnologyInCollaborator(Long idCollaborator, Long idTechnology) {
        return null;

    }

    @Override
    public List<CollaboratorTechnologyEntity> getByCollaboratorId(Long idCollaborator) {
        return null;
    }


}
