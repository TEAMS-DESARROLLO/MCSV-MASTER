package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.models.UnitMeasureEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.IUnitMeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnitMeasureServiceImpl extends CRUDImpl<UnitMeasureEntity,Long> implements IUnitMeasureService {
    private final IGenericRepository<UnitMeasureEntity,Long> repository;

    @Override
    protected IGenericRepository<UnitMeasureEntity, Long> getRepo() {
        return repository;
    }

    @Override
    public UnitMeasureEntity update(UnitMeasureEntity entity, Long id){
        UnitMeasureEntity original = this.readById(id).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idUnitMeasure"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }
}
