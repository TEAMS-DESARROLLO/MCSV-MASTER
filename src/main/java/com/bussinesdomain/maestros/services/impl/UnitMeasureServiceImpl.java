package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.UnitMeasureEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.IUnitMeasureService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnitMeasureServiceImpl extends CRUDImpl<UnitMeasureEntity,Long> implements IUnitMeasureService {
    private final IGenericRepository<UnitMeasureEntity,Long> repository;

    @Override
    protected IGenericRepository<UnitMeasureEntity, Long> getRepo() {
        return repository;
    }
    

    @Override
    public UnitMeasureEntity create(UnitMeasureEntity entidad) {
        return super.create(entidad);
    }


    @Override
    public UnitMeasureEntity update(UnitMeasureEntity entity, Long id){
        UnitMeasureEntity original = this.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idUnitMeasure","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }


    @Override
    public Long count() {
        return repository.findAll().stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).count();
    }


    @Override
    public void deleteById(Long id) {
        UnitMeasureEntity entity = this.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        entity.setRegistrationStatus(RegistrationStatus.INACTIVE);
        super.update(entity, id);
    }


    @Override
    public Boolean exists(Long id) {
        return repository.findById(id).stream().allMatch(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE));
    }


    @Override
    public List<UnitMeasureEntity> getAll() {
        return repository.findAll().stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).collect(Collectors.toList());
    }


    @Override
    public Optional<UnitMeasureEntity> readById(Long id) {
        return repository.findById(id).stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).findFirst() ;
    }
    
}
