package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.exception.RepositoryException;
import com.bussinesdomain.maestros.models.SubpracticaEntity;
import com.bussinesdomain.maestros.models.TechnologyEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ITechnologyService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechnologyServiceImpl extends CRUDImpl<TechnologyEntity,Long> implements ITechnologyService {
    private final IGenericRepository<TechnologyEntity,Long> repository;


    @Override
    protected IGenericRepository<TechnologyEntity, Long> getRepo() {
        return repository;
    }
    @Override
    public Long count() {
        return repository.findAll().stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).count();
    }
    @Override
    public TechnologyEntity create(TechnologyEntity entidad) {
        return super.create(entidad);
    }
    @Override
    public TechnologyEntity update(TechnologyEntity entity,Long id){
        TechnologyEntity original = this.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idTechnology","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }
    @Override
    public Boolean exists(Long id) {
        return repository.findById(id).stream().allMatch(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE));
    }

    @Override
    public void deleteById(Long id) {
        TechnologyEntity entity = this.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        entity.setRegistrationStatus(RegistrationStatus.INACTIVE);
        super.update(entity, id);
    }

    @Override
    public List<TechnologyEntity> getAll() {
        return repository.findAll().stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).collect(Collectors.toList());
    }


    @Override
    public Optional<TechnologyEntity> readById(Long id) {
        return repository.findById(id).stream().filter(x -> x.getRegistrationStatus().equals(RegistrationStatus.ACTIVE)).findFirst() ;
    }
}
