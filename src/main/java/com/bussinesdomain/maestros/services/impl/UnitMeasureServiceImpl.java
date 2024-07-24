package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.UnitMeasureEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.repository.IUnitMeasureBusinessRepository;
import com.bussinesdomain.maestros.services.IUnitMeasureService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnitMeasureServiceImpl extends CRUDImpl<UnitMeasureEntity,Long> implements IUnitMeasureService {
    private final IGenericRepository<UnitMeasureEntity,Long> repository;
    private final IUnitMeasureBusinessRepository businessRepository;

    @Override
    protected IGenericRepository<UnitMeasureEntity, Long> getRepo() {
        return repository;
    }
    

    @Override
    public UnitMeasureEntity create(UnitMeasureEntity entidad) {
        entidad.setRegistrationStatus(RegistrationStatus.ACTIVE);
        return super.create(entidad);
    }


    @Override
    public UnitMeasureEntity update(UnitMeasureEntity entity, Long id){
        UnitMeasureEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idUnitMeasure","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }


    @Override
    public Long count() {
        return businessRepository.countActive();
    }


    @Override
    public void deleteById(Long id) {
        UnitMeasureEntity entity = businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        entity.setRegistrationStatus(RegistrationStatus.INACTIVE);
        super.update(entity, id);
    }


    @Override
    public Boolean exists(Long id) {
        return businessRepository.existsActiveById(id);
    }


    @Override
    public List<UnitMeasureEntity> getAll() {
        return businessRepository.findAllActive();
    }


    @Override
    public UnitMeasureEntity readById(Long id) {
        return businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id)) ;
    }
    
}
