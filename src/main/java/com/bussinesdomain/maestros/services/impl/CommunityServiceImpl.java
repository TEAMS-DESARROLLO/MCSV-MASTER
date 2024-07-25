package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.exception.RepositoryException;
import com.bussinesdomain.maestros.models.CommunityEntity;
import com.bussinesdomain.maestros.repository.ICommunityBusinessRepository;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.repository.ISubpracticaBusinessRepository;
import com.bussinesdomain.maestros.services.ICommunityService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl extends CRUDImpl<CommunityEntity,Long> implements ICommunityService {

    private final IGenericRepository<CommunityEntity, Long> repository;
    private final ICommunityBusinessRepository businessRepository;
    private final ISubpracticaBusinessRepository subpracticalBusinessRepository;
    @Override
    public CommunityEntity create(CommunityEntity entidad) {
        return super.create(entidad);
    }

    @Override
    public CommunityEntity update(CommunityEntity entity, Long id) {
        CommunityEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idCommunity","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }

    @Override
    protected IGenericRepository<CommunityEntity, Long> getRepo() {
        return repository;
    }


    @Override
    public Long count() {
        return businessRepository.countActive();
    }


    @Override
    public void deleteById(Long id) {
        CommunityEntity entity = businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        Boolean existsSubpractica = subpracticalBusinessRepository.underCommunity(id);
        if(existsSubpractica){
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
    public List<CommunityEntity> getAll() {
        return businessRepository.findAllActive();
    }


    @Override
    public CommunityEntity readById(Long id) {
        return businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id)) ;
    }
    
}
