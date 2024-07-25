package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.exception.RepositoryException;
import com.bussinesdomain.maestros.models.RolEntity;
import com.bussinesdomain.maestros.repository.ICollaboratorBusinessRepository;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.IRolService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolServiceImpl extends CRUDImpl<RolEntity,Long> implements IRolService {
    private final IGenericRepository<RolEntity,Long> repository;
    private final ICollaboratorBusinessRepository businessCollaboratorRepository;

    @Override
    protected IGenericRepository<RolEntity, Long> getRepo() {
        return repository;
    }
    @Override
    public RolEntity create(RolEntity entidad) {
        return super.create(entidad);
    }
    @Override
    public RolEntity update(RolEntity entity,Long id){
        RolEntity original = this.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idRol","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }


    @Override
    public Long count() {
        return repository.findAll().stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).count();
    }


    @Override
    public void deleteById(Long id) {
        RolEntity entity = this.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        boolean existsCollaborators = businessCollaboratorRepository.underRol(id).stream().anyMatch(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE));
        if(existsCollaborators){
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
    public List<RolEntity> getAll() {
        return repository.findAll().stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).collect(Collectors.toList());
    }


    @Override
    public Optional<RolEntity> readById(Long id) {
        return repository.findById(id).stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).findFirst() ;
    }
}
