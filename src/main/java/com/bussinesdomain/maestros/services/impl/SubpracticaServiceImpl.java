package com.bussinesdomain.maestros.services.impl;

import org.springframework.beans.BeanUtils;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.SubpracticaEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ISubPracticaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubpracticaServiceImpl  extends CRUDImpl<SubpracticaEntity,Long> implements ISubPracticaService {
    private final IGenericRepository<SubpracticaEntity, Long> repository ;

    @Override
    public SubpracticaEntity update(SubpracticaEntity entity,Long id){
        SubpracticaEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idSubpractica"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(entity,id);
    }

    @Override
    protected IGenericRepository<SubpracticaEntity, Long> getRepo() {
        return repository;
    }

}
