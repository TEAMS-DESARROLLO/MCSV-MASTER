package com.bussinesdomain.maestros.controllers;

import com.bussinesdomain.maestros.dto.CollaboratorTechnologiesRequestDTO;
import com.bussinesdomain.maestros.dto.CollaboratorTechnologyRequestDTO;
import com.bussinesdomain.maestros.dto.CollaboratorTechnologyResponseDTO;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.mapper.ICollaboratorTechnologyMapper;
import com.bussinesdomain.maestros.models.CatalogTechnologyEntity;
import com.bussinesdomain.maestros.models.CollaboratorEntity;
import com.bussinesdomain.maestros.models.CollaboratorTechnologyEntity;
import com.bussinesdomain.maestros.services.ICatalogTechnologyService;
import com.bussinesdomain.maestros.services.ICollaboratorService;
import com.bussinesdomain.maestros.services.ICollaboratorTechnologyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/collaboratorTechnology")
public class CollaboratorTechnologyController {
    private final ICollaboratorService collaboratorService;
    private final ICatalogTechnologyService catalogTechnologyService;
    private final ICollaboratorTechnologyService collaboratorTechnologyService;

    private final ICollaboratorTechnologyMapper collaboratorTechnologyMapper;

    @PostMapping("/findInCollaborator")
    public ResponseEntity<List<CollaboratorTechnologyResponseDTO>> find(@RequestBody CollaboratorTechnologyRequestDTO requestDTO){
        List<CollaboratorTechnologyEntity> lstEntity = collaboratorTechnologyService.getByCollaboratorId(requestDTO.getIdCollaborator());

        List<CollaboratorTechnologyResponseDTO> dtoList = lstEntity.stream().map( entity -> {
            CollaboratorTechnologyResponseDTO dto =collaboratorTechnologyMapper.toGetResponseDTO(entity);
            dto.setIdCollaborator(entity.getCollaborator().getIdCollaborator());
            dto.setCollaboratorNames(entity.getCollaborator().getNames());

            dto.setIdCatalogTechnology(entity.getCatalogTechnology().getIdCatalogTechnology());
            dto.setDescriptionCatalogTechnology(entity.getCatalogTechnology().getDescriptionCatalogTechnology());

            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(dtoList, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
	public ResponseEntity<CollaboratorTechnologyResponseDTO> findById(@PathVariable("id")Long id){
		CollaboratorTechnologyEntity collaboratorTechnologyEntity = this.collaboratorTechnologyService.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id)) ;
		CollaboratorTechnologyResponseDTO collaboratorTechnologyDTO = this.collaboratorTechnologyMapper.toGetResponseDTO(collaboratorTechnologyEntity);
		
        collaboratorTechnologyDTO.setIdCollaborator(collaboratorTechnologyEntity.getCollaborator().getIdCollaborator());
        collaboratorTechnologyDTO.setCollaboratorNames(collaboratorTechnologyEntity.getCollaborator().getNames());

        collaboratorTechnologyDTO.setIdCatalogTechnology(collaboratorTechnologyEntity.getCatalogTechnology().getIdCatalogTechnology());
        collaboratorTechnologyDTO.setDescriptionCatalogTechnology(collaboratorTechnologyEntity.getCatalogTechnology().getDescriptionCatalogTechnology());
        
        return new ResponseEntity<>(collaboratorTechnologyDTO,HttpStatus.OK);
	}

    @PostMapping("/create")
    public ResponseEntity<CollaboratorTechnologyResponseDTO> assignTechnology(@RequestBody CollaboratorTechnologyRequestDTO requestDTO){
        CollaboratorEntity collaboratorEntity = collaboratorService.readById(requestDTO.getIdCollaborator()).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + requestDTO.getIdCollaborator())) ;
        CatalogTechnologyEntity catalogTechnologyEntity = catalogTechnologyService.readById(requestDTO.getIdCatalogTechnology()).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + requestDTO.getIdCatalogTechnology())) ;

        CollaboratorTechnologyEntity entity = collaboratorTechnologyMapper.toEntity(requestDTO);
        entity.setCollaborator(collaboratorEntity);
        entity.setCatalogTechnology(catalogTechnologyEntity);

        CollaboratorTechnologyEntity entitySave =collaboratorTechnologyService.create(entity);
        CollaboratorTechnologyResponseDTO responseviaDTO = this.collaboratorTechnologyMapper.toGetResponseDTO(entitySave);

        responseviaDTO.setIdCollaborator(entitySave.getCollaborator().getIdCollaborator());
        responseviaDTO.setCollaboratorNames(entitySave.getCollaborator().getNames());

        
        responseviaDTO.setIdCatalogTechnology(entitySave.getCatalogTechnology().getIdCatalogTechnology());
        responseviaDTO.setDescriptionCatalogTechnology(entitySave.getCatalogTechnology().getDescriptionCatalogTechnology());
        return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{idCollaboratorTechnology}")
    public ResponseEntity<CollaboratorTechnologyResponseDTO> update(@Validated @PathVariable("idCollaboratorTechnology") Long idCollaboratorTechnology,
                                                       @RequestBody CollaboratorTechnologyRequestDTO requestDTO){
        CollaboratorTechnologyEntity objEntitySource = this.collaboratorTechnologyMapper.toEntity(requestDTO);

        CollaboratorEntity collaboratorEntity = collaboratorService.readById(requestDTO.getIdCollaborator()).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + requestDTO.getIdCollaborator())) ;
        CatalogTechnologyEntity catalogTechnologyEntity = catalogTechnologyService.readById(requestDTO.getIdCatalogTechnology()).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + requestDTO.getIdCatalogTechnology())) ;


        objEntitySource.setCollaborator(collaboratorEntity);
        objEntitySource.setCatalogTechnology(catalogTechnologyEntity);

        CollaboratorTechnologyEntity obj =  collaboratorTechnologyService.update(objEntitySource, idCollaboratorTechnology);
        CollaboratorTechnologyResponseDTO responseviaDTO = this.collaboratorTechnologyMapper.toGetResponseDTO(obj);

        responseviaDTO.setIdCollaborator(obj.getCollaborator().getIdCollaborator());
        responseviaDTO.setCollaboratorNames(obj.getCollaborator().getNames());

        
        responseviaDTO.setIdCatalogTechnology(obj.getCatalogTechnology().getIdCatalogTechnology());
        responseviaDTO.setDescriptionCatalogTechnology(obj.getCatalogTechnology().getDescriptionCatalogTechnology());
        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
    }

    @PostMapping("/createMultiple")
    public ResponseEntity<List<CollaboratorTechnologyResponseDTO>> assignTechnology( @RequestBody CollaboratorTechnologiesRequestDTO requestDTO){
        CollaboratorEntity collaboratorEntity = collaboratorService.readById(requestDTO.getIdCollaborator()).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + requestDTO.getIdCollaborator())) ;

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
    public ResponseEntity<CollaboratorTechnologyResponseDTO> delete(@PathVariable("idCollaboratorTechnology") Long idCollaboratorTechnology){
        CollaboratorTechnologyEntity entity = collaboratorTechnologyService.readById(idCollaboratorTechnology).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + idCollaboratorTechnology)) ;
        collaboratorTechnologyService.delete(entity);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

}
