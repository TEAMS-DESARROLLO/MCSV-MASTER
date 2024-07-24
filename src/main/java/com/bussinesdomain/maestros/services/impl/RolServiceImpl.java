package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.RolEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.repository.IRolBusinessRepository;
import com.bussinesdomain.maestros.services.IRolService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolServiceImpl extends CRUDImpl<RolEntity,Long> implements IRolService {
    private final IGenericRepository<RolEntity,Long> repository;
    private final IRolBusinessRepository businessRepository;

    @Override
    protected IGenericRepository<RolEntity, Long> getRepo() {
        return repository;
    }
    @Override
    public RolEntity create(RolEntity entidad) {
        entidad.setRegistrationStatus(RegistrationStatus.ACTIVE);
        return super.create(entidad);
    }
    @Override
    public RolEntity update(RolEntity entity,Long id){
        RolEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idRol","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }


    @Override
    public Long count() {
        return businessRepository.countActive();
    }


    @Override
    public void deleteById(Long id) {
        RolEntity entity = businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        entity.setRegistrationStatus(RegistrationStatus.INACTIVE);
        super.update(entity, id);
    }


    @Override
    public Boolean exists(Long id) {
        return businessRepository.existsActiveById(id);
    }


    @Override
    public List<RolEntity> getAll() {
        return businessRepository.findAllActive();
    }


    @Override
    public RolEntity readById(Long id) {
        return businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id)) ;
    }
}
