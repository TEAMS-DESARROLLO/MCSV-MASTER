package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.dto.LeaderResponseDTO;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.mapper.ILeaderMapper;
import com.bussinesdomain.maestros.models.LeaderEntity;
import com.bussinesdomain.maestros.repository.IGenericRepository;
import com.bussinesdomain.maestros.repository.ILeaderRepository;
import com.bussinesdomain.maestros.services.ILeaderService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LeaderServiceImpl extends CRUDImpl<LeaderEntity,Long> implements ILeaderService {

    private final IGenericRepository<LeaderEntity,Long> repository;
    private final ILeaderMapper iLeaderMapper;
    private final ILeaderRepository iLeaderRepository;

    @Override
    public LeaderEntity update(LeaderEntity entity, Long id) {
        LeaderEntity original = this.readById(id).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null); ;
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idLeader"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original, id);
    }

    @Override
    protected IGenericRepository<LeaderEntity, Long> getRepo() {
        return repository;
    }

    @Override
    public List<LeaderResponseDTO> leadersByIds(List<Long> ids) {
        List<LeaderEntity> leaders = repository.findAllById(ids);
        List<LeaderResponseDTO> leadersDto = iLeaderMapper.listEntityToResponseDTO(leaders);
        return leadersDto;

    }

    @Override
    public LeaderEntity getLeaderEntityByIdPractice(Long idPractice) {
        return iLeaderRepository.getLeaderEntityByIdPractice(idPractice);
    }

}
