package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.constants.RegistrationStatus;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.TechnologyEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.ITechnologyService;
import lombok.RequiredArgsConstructor;
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
    public TechnologyEntity create(TechnologyEntity entidad) {
        entidad.setRegistrationStatus(RegistrationStatus.ACTIVE);
        return super.create(entidad);
    }
    @Override
    public TechnologyEntity update(TechnologyEntity entity,Long id){
        TechnologyEntity original = this.readById(id);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idTechnology","createdAt","registrationStatus"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }
}
