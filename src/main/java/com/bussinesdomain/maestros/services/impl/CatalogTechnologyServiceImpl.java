package com.bussinesdomain.maestros.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.exception.RepositoryException;
import com.bussinesdomain.maestros.models.CatalogTechnologyEntity;
import com.bussinesdomain.maestros.repository.ICollaboratorTechnologyBusinessRepository;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ICatalogTechnologyService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CatalogTechnologyServiceImpl extends CRUDImpl<CatalogTechnologyEntity,Long> implements ICatalogTechnologyService {
    private final IGenericRepository<CatalogTechnologyEntity,Long> repository;
    private final ICollaboratorTechnologyBusinessRepository collaboratorTechnologyBusinessRepository;

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
        CatalogTechnologyEntity original = this.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idCatalogTechnology","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }


    @Override
    public Long count() {
        return repository. findAll().stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).count();
    }


    @Override
    public void deleteById(Long id) {
        CatalogTechnologyEntity entity = this.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        Boolean existsCollaboratorTechnology = collaboratorTechnologyBusinessRepository.underCatalogTechnology(id).stream().anyMatch(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE));
        if(existsCollaboratorTechnology){
           throw new RepositoryException("ERROR WHILE DELETING, CHECK IF THERE ARE FOREIGN KEYS RELATED TO THE ROW");
        }
        entity.setRegistrationStatus(RegistrationStatus.INACTIVE);
        super.update(entity, id);
    }


    @Override
    public Boolean exists(Long id) {
        return repository.findById(id).stream().allMatch(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE));
    }


    @Override
    public List<CatalogTechnologyEntity> getAll() {
        return repository.findAll().stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).collect(Collectors.toList());
    }


    @Override
    public Optional<CatalogTechnologyEntity> readById(Long id) {
        return repository.findById(id).stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).findFirst() ;
    }
    
}
