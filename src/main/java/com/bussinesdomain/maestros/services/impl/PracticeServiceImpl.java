package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.dto.PracticeResponseDTO;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.mapper.IPracticeMapper;
import com.bussinesdomain.maestros.models.PracticeEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.services.IPracticeService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PracticeServiceImpl extends CRUDImpl<PracticeEntity,Long> implements IPracticeService {

    private final IGenericRepository<PracticeEntity, Long> repository;
    private final IPracticeMapper iPracticeMapper;

    @Override
    public PracticeEntity update(PracticeEntity entity, Long id) {
        PracticeEntity original = this.readById(id).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);;
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"createdAt","updatedAt","idPractice"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }

    @Override
    protected IGenericRepository<PracticeEntity, Long> getRepo() {
        return repository;
    }

    @Override
    public List<PracticeResponseDTO> practicesByIds(List<Long> ids) {
        List<PracticeEntity> practices = repository.findAllById(ids);
        List<PracticeResponseDTO> practicesDto = iPracticeMapper.listEntityToResponseDTO(practices);
        return practicesDto;

    }
}
