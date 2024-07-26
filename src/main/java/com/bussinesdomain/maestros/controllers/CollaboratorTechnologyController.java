package com.bussinesdomain.maestros.controllers;

import com.bussinesdomain.maestros.dto.CollaboratorTechnologiesRequestDTO;
import com.bussinesdomain.maestros.dto.CollaboratorTechnologyRequestDTO;
import com.bussinesdomain.maestros.dto.CollaboratorTechnologyResponseDTO;
import com.bussinesdomain.maestros.mapper.ICollaboratorTechnologyMapper;
import com.bussinesdomain.maestros.models.CollaboratorEntity;
import com.bussinesdomain.maestros.models.CollaboratorTechnologyEntity;

import com.bussinesdomain.maestros.services.ICollaboratorService;
import com.bussinesdomain.maestros.services.ICollaboratorTechnologyService;
import com.bussinesdomain.maestros.services.ITechnologyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/collaboratorTechnology")
public class CollaboratorTechnologyController {
    private final ICollaboratorService collaboratorService;
    private final ITechnologyService technologyService;
    private final ICollaboratorTechnologyService collaboratorTechnologyService;

    private final ICollaboratorTechnologyMapper collaboratorTechnologyMapper;

    @PostMapping("/findInCollaborator")
    public ResponseEntity<List<CollaboratorTechnologyResponseDTO>> find(@RequestBody CollaboratorTechnologyRequestDTO requestDTO){
        List<CollaboratorTechnologyEntity> lstEntity = collaboratorTechnologyService.getByCollaboratorId(requestDTO.getIdCollaborator());

        List<CollaboratorTechnologyResponseDTO> dtoList = lstEntity.stream().map( entity -> {
            CollaboratorTechnologyResponseDTO dto =collaboratorTechnologyMapper.toGetResponseDTO(entity);
            dto.setIdCollaborator(entity.getCollaborator().getIdCollaborator());
            dto.setCollaboratorNames(entity.getCollaborator().getNames());

            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<CollaboratorTechnologyResponseDTO> assignTechnology(@RequestBody CollaboratorTechnologyRequestDTO requestDTO){
        CollaboratorEntity collaboratorEntity = collaboratorService.readById(requestDTO.getIdCollaborator()).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);
        //TechnologyEntity technologyEntity = technologyService.readById(requestDTO.getIdTechnology()).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);    

        //CollaboratorTechnologyEntity entity = collaboratorTechnologyMapper.toEntity(requestDTO);
        //entity.setCollaborator(collaboratorEntity);

        //CollaboratorTechnologyEntity entitySave =collaboratorTechnologyService.create(entity);
        //CollaboratorTechnologyResponseDTO responseviaDTO = this.collaboratorTechnologyMapper.toGetResponseDTO(entitySave);

        // responseviaDTO.setIdCollaborator(entitySave.getCollaborator().getIdCollaborator());
        // responseviaDTO.setCollaboratorNames(entitySave.getCollaborator().getNames());
        //return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
        return null;
    }

    @PostMapping("/createMultiple")
    public ResponseEntity<List<CollaboratorTechnologyResponseDTO>> assignTechnology( @RequestBody CollaboratorTechnologiesRequestDTO requestDTO){
        //CollaboratorEntity collaboratorEntity = collaboratorService.readById(requestDTO.getIdCollaborator());

        // List<CollaboratorTechnologyEntity> entities = new ArrayList<CollaboratorTechnologyEntity>();
        // for(Long idTechnology : requestDTO.getLstIdTechnologies()){
        //     CollaboratorTechnologyEntity entity = new CollaboratorTechnologyEntity();
        //     entity.setCollaborator(collaboratorEntity);
        //     TechnologyEntity technologyEntity = new TechnologyEntity();
        //     technologyEntity.setIdTechnology(idTechnology);
        //     entity.setTechnology(technologyEntity);
        //     entities.add(entity);
        // }
        // List<CollaboratorTechnologyEntity> entitiesSave =collaboratorTechnologyService.createAll(entities);
        // List<CollaboratorTechnologyResponseDTO> responseviaDTO = entitiesSave.stream().map( entity -> {
        //     CollaboratorTechnologyResponseDTO dto =collaboratorTechnologyMapper.toGetResponseDTO(entity);
        //     dto.setIdCollaborator(entity.getCollaborator().getIdCollaborator());
        //     dto.setCollaboratorNames(entity.getCollaborator().getNames());
        //     dto.setIdTechnology(entity.getTechnology().getIdTechnology());
        //     dto.setTechnologyName(entity.getTechnology().getName());
        //     return dto;
        // }).collect(Collectors.toList());
        // return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
        return null;
    }

    @DeleteMapping("/{idCollaboratorTechnology}")
    public ResponseEntity<CollaboratorTechnologyResponseDTO> unAssignTechnology(@PathVariable("idCollaboratorTechnology") Long idCollaboratorTechnology){
        CollaboratorTechnologyEntity entity = collaboratorTechnologyService.readById(idCollaboratorTechnology).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);  
        collaboratorTechnologyService.delete(entity);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

}
