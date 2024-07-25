package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.exception.RepositoryException;
import com.bussinesdomain.maestros.models.RegionEntity;
import com.bussinesdomain.maestros.repository.ICollaboratorBusinessRepository;
import com.bussinesdomain.maestros.repository.ICommunityBusinessRepository;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.repository.IRegionBusinessRepository;
import com.bussinesdomain.maestros.services.IRegionService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl extends CRUDImpl<RegionEntity,Long> implements IRegionService {
    private final IGenericRepository<RegionEntity,Long> repository;
    private final IRegionBusinessRepository businessRepository;
    private final ICollaboratorBusinessRepository businessCollaboratorRepository;
    private final ICommunityBusinessRepository businessRegionRepository;
    @Override
    protected IGenericRepository<RegionEntity, Long> getRepo() {
        return repository;
    }
    @Override
    public RegionEntity create(RegionEntity entidad) {
        return super.create(entidad);
    }
    @Override
    public RegionEntity update(RegionEntity entity, Long id){
        RegionEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idRegion","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }


    @Override
    public Long count() {
        return businessRepository.countActive();
    }


    @Override
    public void deleteById(Long id) {
        RegionEntity entity = businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        boolean existsCollaborators = businessCollaboratorRepository.underRegion(id);
        if(existsCollaborators){
            throw new RepositoryException("ERROR WHILE DELETING, CHECK IF THERE ARE FOREIGN KEYS RELATED TO THE ROW");
        }
        boolean existsCommunities = businessRegionRepository.underRegion(id);
        if(existsCommunities){
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
    public List<RegionEntity> getAll() {
        return businessRepository.findAllActive();
    }


    @Override
    public RegionEntity readById(Long id) {
        return businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id)) ;
    }
}

