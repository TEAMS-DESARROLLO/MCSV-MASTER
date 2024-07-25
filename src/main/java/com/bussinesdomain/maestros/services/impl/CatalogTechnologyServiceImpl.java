package com.bussinesdomain.maestros.services.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.CatalogTechnologyEntity;
import com.bussinesdomain.maestros.repository.ICatalogTechnologyBusinessRepository;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ICatalogTechnologyService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CatalogTechnologyServiceImpl extends CRUDImpl<CatalogTechnologyEntity,Long> implements ICatalogTechnologyService {
    private final IGenericRepository<CatalogTechnologyEntity,Long> repository;
    private final ICatalogTechnologyBusinessRepository businessRepository;

    @Override
    protected IGenericRepository<CatalogTechnologyEntity, Long> getRepo() {
        return repository;
    }
    

    @Override
    public CatalogTechnologyEntity create(CatalogTechnologyEntity entidad) {
        return super.create(entidad);
    }


    @Override
    public CatalogTechnologyEntity update(CatalogTechnologyEntity entity, Long id){
        CatalogTechnologyEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idCatalogTechnology","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }


    @Override
    public Long count() {
        return businessRepository.countActive();
    }


    @Override
    public void deleteById(Long id) {
        CatalogTechnologyEntity entity = businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        entity.setRegistrationStatus(RegistrationStatus.INACTIVE);
        super.update(entity, id);
    }


    @Override
    public Boolean exists(Long id) {
        return businessRepository.existsActiveById(id);
    }


    @Override
    public List<CatalogTechnologyEntity> getAll() {
        return businessRepository.findAllActive();
    }


    @Override
    public CatalogTechnologyEntity readById(Long id) {
        return businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id)) ;
    }
    
}
