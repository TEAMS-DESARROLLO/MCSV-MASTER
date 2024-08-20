package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.dto.RegionResponseDTO;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.mapper.IRegionMapper;
import com.bussinesdomain.maestros.models.RegionEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.IRegionService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl extends CRUDImpl<RegionEntity,Long> implements IRegionService {
    private final IGenericRepository<RegionEntity,Long> repository;
    private final IRegionMapper iRegionMapper;


    @Override
    protected IGenericRepository<RegionEntity, Long> getRepo() {
        return repository;
    }
    @Override
    public RegionEntity update(RegionEntity entity, Long id){
        RegionEntity original = this.readById(id).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null); ;
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idRegion"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }

    @Override
    public List<RegionResponseDTO> regionsByIds(List<Long> ids) {
            
            List<RegionEntity> regions = repository.findAllById(ids);
    
            List<RegionResponseDTO> regionsDto = iRegionMapper.listEntityToDTO(regions);
            return regionsDto;

    }
}

