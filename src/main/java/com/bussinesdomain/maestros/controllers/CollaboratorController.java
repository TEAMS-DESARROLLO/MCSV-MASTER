package com.bussinesdomain.maestros.controllers;


import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.CollaboratorRequestDTO;
import com.bussinesdomain.maestros.dto.CollaboratorResponseDTO;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.mapper.ICollaboratorMapper;
import com.bussinesdomain.maestros.models.CollaboratorEntity;
import com.bussinesdomain.maestros.models.FunctionalLeaderEntity;
import com.bussinesdomain.maestros.models.LeaderEntity;
import com.bussinesdomain.maestros.models.RegionEntity;
import com.bussinesdomain.maestros.models.RolEntity;
import com.bussinesdomain.maestros.services.ICollaboratorService;
import com.bussinesdomain.maestros.services.IFunctionalLeaderService;
import com.bussinesdomain.maestros.services.ILeaderService;
import com.bussinesdomain.maestros.services.IRegionService;
import com.bussinesdomain.maestros.services.IRolService;
import com.bussinesdomain.maestros.services.impl.CollaboratorPaginationService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/collaborator")
public class CollaboratorController {

    private final ICollaboratorService collaboratorService;
    private final ICollaboratorMapper collaboratorMapper;

    private final ILeaderService leaderService;
    private final IRolService rolService;
    private final IRegionService regionService;
    private final IFunctionalLeaderService functionalLeaderService;


    private final CollaboratorPaginationService iCollaboratorPaginationService;


    @PostMapping("/pagination")
    public ResponseEntity<?> paginador( @RequestBody PaginationModel pagination ){
        Page<CollaboratorResponseDTO> lst = iCollaboratorPaginationService.pagination(pagination);
        return new ResponseEntity<>(lst, HttpStatus.OK) ;
    }
    @GetMapping("/{idCollaborator}")
    public ResponseEntity<CollaboratorResponseDTO> findById(@PathVariable("idCollaborator") Long idCollaborator){

        //CollaboratorEntity obj = this.collaboratorService.readById(idCollaborator).get();
        CollaboratorResponseDTO dto = this.collaboratorService.collaboratorByIdquery(idCollaborator);

        

        // dto.setIdLeader(obj.getLeader().getIdLeader());
        // dto.setLeaderNames(obj.getLeader().getNames());

        // dto.setIdRol(obj.getRol().getIdRol());
        // dto.setRolDescription(obj.getRol().getDescription());

        // dto.setIdRegion(obj.getRegion().getIdRegion());
        // dto.setRegionDescription(obj.getRegion().getDescription());

        // dto.setIdFunctionalLeader(obj.getFunctionalLeader().getIdFunctionalLeader());
        // dto.setFunctionalLeaderNames(obj.getFunctionalLeader().getNames());

        // dto.setIdStatusCollaborator(obj.getStatusCollaborator().getIdStatusCollaborator());
        // dto.setDescriptionStatusCollaborator( obj.getStatusCollaborator().getDescriptionStatusCollaborator() );

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

  
    
    @PostMapping("/create")
    public ResponseEntity<CollaboratorResponseDTO> save(@Validated @RequestBody CollaboratorRequestDTO requestDTO) {

        LeaderEntity leaderEntity =  leaderService.readById(requestDTO.getIdLeader()).get();

        RolEntity rolEntity = rolService.readById(requestDTO.getIdRol()).stream().filter(p -> p.getRegistrationStatus().equals("A")).findFirst().orElse(null);

        RegionEntity regionEntity = regionService.readById(requestDTO.getIdRegion()).stream().filter(p -> p.getRegistrationStatus().equals("A")).findFirst().orElse(null);  

        FunctionalLeaderEntity functionalLeaderEntity = functionalLeaderService.readById( requestDTO.getIdFunctionalLeader() ).stream().filter(p -> p.getRegistrationStatus().equals("A")).findFirst().orElse(null);

        CollaboratorEntity entidad = this.collaboratorMapper.toEntity(requestDTO);

        entidad.setLeader(leaderEntity);
        entidad.setRol(rolEntity);
        entidad.setRegion(regionEntity);
        entidad.setFunctionalLeader(functionalLeaderEntity);

        CollaboratorEntity entidadSave = this.collaboratorService.create( entidad);
        CollaboratorResponseDTO responseviaDTO = this.collaboratorMapper.toGetResponseDTO(entidadSave);

        responseviaDTO.setIdLeader(entidadSave.getLeader().getIdLeader());
        responseviaDTO.setLeaderNames(entidadSave.getLeader().getNames());

        responseviaDTO.setIdRol(entidadSave.getRol().getIdRol());
        responseviaDTO.setRolDescription(entidadSave.getRol().getDescription());

        responseviaDTO.setIdRegion(entidadSave.getRegion().getIdRegion());
        responseviaDTO.setRegionDescription(entidadSave.getRegion().getDescription());

        responseviaDTO.setIdFunctionalLeader(entidadSave.getFunctionalLeader().getIdFunctionalLeader());
        responseviaDTO.setFunctionalLeaderNames(entidadSave.getFunctionalLeader().getNames());
        return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{idCollaborator}")
    public ResponseEntity<CollaboratorResponseDTO> update(@Validated @PathVariable("idCollaborator") Long idCollaborator,
                                                    @RequestBody CollaboratorRequestDTO requestDTO){

        LeaderEntity leaderEntity =  leaderService.readById(requestDTO.getIdLeader()).stream().filter(p -> p.getRegistrationStatus().equals("A")).findFirst().orElse(null);

        if(leaderEntity==null){
            throw new  ModelNotFoundException("No se encontro el registro solicitado, o status no es activo");
        }
        


        RolEntity rolEntity = rolService.readById(requestDTO.getIdRol()).stream().filter(p -> p.getRegistrationStatus().equals("A")).findFirst().orElse(null);

        RegionEntity regionEntity = regionService.readById(requestDTO.getIdRegion()).stream().filter(p -> p.getRegistrationStatus().equals("A")).findFirst().orElse(null);

        FunctionalLeaderEntity functionalLeaderEntity = functionalLeaderService.readById(requestDTO.getIdFunctionalLeader()).stream().filter(p -> p.getRegistrationStatus().equals("A")).findFirst().orElse(null);

        CollaboratorEntity objEntitySource = this.collaboratorMapper.toEntity(requestDTO);

        objEntitySource.setLeader(leaderEntity);
        objEntitySource.setRol(rolEntity);
        objEntitySource.setRegion(regionEntity);
        objEntitySource.setFunctionalLeader(functionalLeaderEntity);

        CollaboratorEntity obj =  collaboratorService.update(objEntitySource, idCollaborator);
        CollaboratorResponseDTO responseviaDTO = this.collaboratorMapper.toGetResponseDTO(obj);

        responseviaDTO.setIdLeader(obj.getLeader().getIdLeader());
        responseviaDTO.setLeaderNames(obj.getLeader().getNames());

        responseviaDTO.setIdRol(obj.getRol().getIdRol());
        responseviaDTO.setRolDescription(obj.getRol().getDescription());

        responseviaDTO.setIdRegion(obj.getRegion().getIdRegion());
        responseviaDTO.setRegionDescription(obj.getRegion().getDescription());

        responseviaDTO.setIdFunctionalLeader(obj.getFunctionalLeader().getIdFunctionalLeader());
        responseviaDTO.setFunctionalLeaderNames(obj.getFunctionalLeader().getNames());
        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idCollaborator}")
    public ResponseEntity<CollaboratorResponseDTO> delete(@PathVariable("idCollaborator") Long idCollaborator){
        collaboratorService.deleteById(idCollaborator);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    
    @PostMapping("/collaboratorsByIds")
    public ResponseEntity<List<CollaboratorResponseDTO>> collaboratorsByIds(@RequestParam("ids") List<Long> ids){
        List<CollaboratorResponseDTO> lista = collaboratorService.collaboratorsByIds(ids);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
