package com.bussinesdomain.maestros.services.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.StatusCollaboratorEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.repository.IStatusCollaboratorBusinessRepository;
import com.bussinesdomain.maestros.services.IStatusCollaboratorService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class StatusCollaboratorServiceImpl extends CRUDImpl<StatusCollaboratorEntity,Long> implements IStatusCollaboratorService {
    private final IGenericRepository<StatusCollaboratorEntity,Long> repository;
    private final IStatusCollaboratorBusinessRepository businessRepository;

    @Override
    protected IGenericRepository<StatusCollaboratorEntity, Long> getRepo() {
        return repository;
    }
    

    @Override
    public StatusCollaboratorEntity create(StatusCollaboratorEntity entidad) {
        return super.create(entidad);
    }


    @Override
    public StatusCollaboratorEntity update(StatusCollaboratorEntity entity, Long id){
        StatusCollaboratorEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idStatusCollaborator","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }


    @Override
    public Long count() {
        return businessRepository.countActive();
    }


    @Override
    public void deleteById(Long id) {
        StatusCollaboratorEntity entity = businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        entity.setRegistrationStatus(RegistrationStatus.INACTIVE);
        super.update(entity, id);
    }


    @Override
    public Boolean exists(Long id) {
        return businessRepository.existsActiveById(id);
    }


    @Override
    public List<StatusCollaboratorEntity> getAll() {
        return businessRepository.findAllActive();
    }


    @Override
    public StatusCollaboratorEntity readById(Long id) {
        return businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id)) ;
    }
    
}

