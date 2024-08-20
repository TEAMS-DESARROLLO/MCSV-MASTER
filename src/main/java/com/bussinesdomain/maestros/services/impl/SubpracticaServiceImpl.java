package com.bussinesdomain.maestros.services.impl;

import org.springframework.beans.BeanUtils;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.SpecializationEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ISubPracticaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SubpracticaServiceImpl  extends CRUDImpl<SpecializationEntity,Long> implements ISubPracticaService {
    private final IGenericRepository<SpecializationEntity, Long> repository ;

    @Override
    public SpecializationEntity update(SpecializationEntity entity,Long id){
        SpecializationEntity original = this.readById(id).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);;
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idSubpractica"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(entity,id);
    }

    @Override
    protected IGenericRepository<SpecializationEntity, Long> getRepo() {
        return repository;
    }

}
