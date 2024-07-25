package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.exception.RepositoryException;
import com.bussinesdomain.maestros.models.LeaderEntity;
import com.bussinesdomain.maestros.repository.ICollaboratorBusinessRepository;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ILeaderService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaderServiceImpl extends CRUDImpl<LeaderEntity,Long> implements ILeaderService {

    private final IGenericRepository<LeaderEntity,Long> repository;
    private final ICollaboratorBusinessRepository businessCollaboratorRepository;
    @Override
    public LeaderEntity create(LeaderEntity entidad) {
        return super.create(entidad);
    }

    @Override
    public LeaderEntity update(LeaderEntity entity, Long id) {
        LeaderEntity original = this.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idLeader","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original, id);
    }

    @Override
    protected IGenericRepository<LeaderEntity, Long> getRepo() {
        return repository;
    }


    @Override
    public Long count() {
        return repository.findAll().stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).count();
    }


    @Override
    public void deleteById(Long id) {
        LeaderEntity entity = this.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        boolean existsCollaborators = businessCollaboratorRepository.underLeader(id).stream().anyMatch(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE));
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
    public List<LeaderEntity> getAll() {
        return repository.findAll().stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).collect(Collectors.toList());
    }


    @Override
    public Optional<LeaderEntity> readById(Long id) {
        return repository.findById(id).stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).findFirst() ;
    }
    
}
