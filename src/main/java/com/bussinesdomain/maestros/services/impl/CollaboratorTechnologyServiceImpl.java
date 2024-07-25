package com.bussinesdomain.maestros.services.impl;


import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.CollaboratorTechnologyEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ICollaboratorTechnologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollaboratorTechnologyServiceImpl extends CRUDImpl<CollaboratorTechnologyEntity,Long> implements ICollaboratorTechnologyService {
    private final IGenericRepository<CollaboratorTechnologyEntity,Long> repository;
    @Override
    public CollaboratorTechnologyEntity create(CollaboratorTechnologyEntity entidad) {
        return super.create(entidad);
    }


    @Override
    protected IGenericRepository<CollaboratorTechnologyEntity, Long> getRepo() {
        return repository;
    }


    @Override
    public CollaboratorTechnologyEntity update(CollaboratorTechnologyEntity entity,Long id){
        CollaboratorTechnologyEntity original = this.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idCollaboratorTechnology","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(entity,id);
    }


    @Override
    public Long count() {
        return repository.findAll().stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).count();
    }


    @Override
    public void deleteById(Long id) {
        CollaboratorTechnologyEntity entity = this.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        entity.setRegistrationStatus(RegistrationStatus.INACTIVE);
        super.update(entity, id);
    }


    @Override
    public Boolean exists(Long id) {
        return repository.findById(id).stream().allMatch(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE));
    }


    @Override
    public List<CollaboratorTechnologyEntity> getAll() {
        return repository.findAll().stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).collect(Collectors.toList());
    }


    @Override
    public Optional<CollaboratorTechnologyEntity> readById(Long id) {
        return repository.findById(id).stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).findFirst() ;
    }

    @Override
    public CollaboratorTechnologyEntity getTechnologyInCollaborator(Long idCollaborator, Long idTechnology) {
        //return businessRepository.getTechnologyInCollaborator(idCollaborator,idTechnology);
        return null;
    }

    @Override
    public List<CollaboratorTechnologyEntity> getByCollaboratorId(Long idCollaborator) {
        //return businessRepository.getByCollaboratorId(idCollaborator);
        return null;
    }
}
