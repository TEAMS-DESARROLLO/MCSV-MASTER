package com.bussinesdomain.maestros.services.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.exception.RepositoryException;
import com.bussinesdomain.maestros.models.SubpracticaEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.repository.ISubpracticaBusinessRepository;
import com.bussinesdomain.maestros.repository.ITechnologyBusinessRepository;
import com.bussinesdomain.maestros.services.ISubPracticaService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SubpracticaServiceImpl  extends CRUDImpl<SubpracticaEntity,Long> implements ISubPracticaService {
    private final IGenericRepository<SubpracticaEntity, Long> repository ;
    private final ISubpracticaBusinessRepository businessRepository;
    private final ITechnologyBusinessRepository businessTechnologyRepository;
    @Override
    public SubpracticaEntity create(SubpracticaEntity entidad) {
        return super.create(entidad);
    }

    @Override
    public SubpracticaEntity update(SubpracticaEntity entity,Long id){
        SubpracticaEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idSubpractica","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }

    @Override
    protected IGenericRepository<SubpracticaEntity, Long> getRepo() {
        return repository;
    }

    @Override
    public Long count() {
        return businessRepository.countActive();
    }


    @Override
    public void deleteById(Long id) {
        SubpracticaEntity entity = businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id )) ;
        Boolean existsTechnology = this.businessTechnologyRepository.underSubpractica(id);
        if(existsTechnology){
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
    public List<SubpracticaEntity> getAll() {
        return businessRepository.findAllActive();
    }


    @Override
    public SubpracticaEntity readById(Long id) {
        return businessRepository.findActiveById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id)) ;
    }
    

}
