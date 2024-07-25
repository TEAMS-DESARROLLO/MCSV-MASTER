package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.exception.RepositoryException;
import com.bussinesdomain.maestros.models.FunctionalLeaderEntity;
import com.bussinesdomain.maestros.repository.ICollaboratorBusinessRepository;
import com.bussinesdomain.maestros.repository.IFunctionalLeaderBusinessRepository;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.IFunctionalLeaderService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FunctionalLeaderServiceImpl extends CRUDImpl<FunctionalLeaderEntity,Long> implements IFunctionalLeaderService {
    private final IGenericRepository<FunctionalLeaderEntity,Long> repository;
    private final IFunctionalLeaderBusinessRepository businessRepository;
    private final ICollaboratorBusinessRepository businessCollaboratorRepository;
    @Override
    public FunctionalLeaderEntity create(FunctionalLeaderEntity entidad) {
        return super.create(entidad);
    }

    @Override
    public FunctionalLeaderEntity update(FunctionalLeaderEntity entity,Long id){
        FunctionalLeaderEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idFunctionalLeader","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }

    @Override
    protected IGenericRepository<FunctionalLeaderEntity, Long> getRepo() {
        return repository;
    }


    @Override
    public Long count() {
        return businessRepository.countActive();
    }


    @Override
    public void deleteById(Long id) {
        FunctionalLeaderEntity entity = businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        
        boolean existsCollaborators = businessCollaboratorRepository.underFunctionalLeader(id);
        if(existsCollaborators){
            throw new RepositoryException("ERROR WHILE DELETING, CHECK IF THERE ARE FOREIGN KEYS RELATED TO THE ROW");
        }
        entity.setRegistrationStatus(RegistrationStatus.INACTIVE);
        super.update(entity, id);
    }


    @Override
    public Boolean exists(Long id) {
        return businessRepository.existsActiveById(id);
    }


    @Override
    public List<FunctionalLeaderEntity> getAll() {
        return businessRepository.findAllActive();
    }


    @Override
    public FunctionalLeaderEntity readById(Long id) {
        return businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id)) ;
    }
}
