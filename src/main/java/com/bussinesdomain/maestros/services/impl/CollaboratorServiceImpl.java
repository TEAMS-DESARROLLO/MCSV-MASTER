package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.dto.CollaboratorResponseDTO;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.mapper.ICollaboratorMapper;
import com.bussinesdomain.maestros.models.CollaboratorEntity;
import com.bussinesdomain.maestros.repository.ICollaboratorBusinessRepository;
import com.bussinesdomain.maestros.repository.IGenericRepository;

import com.bussinesdomain.maestros.services.ICollaboratorService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollaboratorServiceImpl extends CRUDImpl<CollaboratorEntity,Long> implements ICollaboratorService {
    private final IGenericRepository<CollaboratorEntity,Long> repository;


    private final ICollaboratorMapper iCollaboratorMapper;

    private final ICollaboratorBusinessRepository iCollaboratorBusinessRepository;

    @Override
    protected IGenericRepository<CollaboratorEntity, Long> getRepo() {
        return repository;
    }

    @Override
    public CollaboratorEntity update(CollaboratorEntity entity, Long id){
        CollaboratorEntity original = this.readById(id).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);
        if(original.equals(null)){
            throw new ModelNotFoundException("The following ID does not exists : " + id);
        }
        String[] ignoreProperties= new String[]{"idCollaborator"};
        BeanUtils.copyProperties(entity,original,ignoreProperties);
        return super.update(original,id);
    }

    @Override
    public List<CollaboratorResponseDTO> collaboratorsByIds(List<Long> ids) {


        List<CollaboratorResponseDTO> collaboratosDto = iCollaboratorBusinessRepository.collaboratorsByIds(ids);

        return collaboratosDto;
    }

    @Override
    public CollaboratorResponseDTO collaboratorByIdquery(Long id) {
        return iCollaboratorBusinessRepository.collaboratorByIdquery(id);
    }
}
