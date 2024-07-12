package com.bussinesdomain.maestros.controllers;

import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.*;
import com.bussinesdomain.maestros.mapper.ICollaboratorMapper;
import com.bussinesdomain.maestros.mapper.ICollaboratorTechnologyMapper;
import com.bussinesdomain.maestros.models.*;
import com.bussinesdomain.maestros.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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



    private final IPaginationCommons<CollaboratorDTO> iPaginationCommons;

    @PostMapping("/pagination")
    public ResponseEntity<?> paginador(@RequestBody PaginationModel pagination ){
        Page<CollaboratorDTO> lst = iPaginationCommons.pagination(pagination);
        return new ResponseEntity<>(lst, HttpStatus.OK) ;
    }
    @GetMapping("/{idLeader}")
    public ResponseEntity<CollaboratorResponseDTO> findById(@PathVariable("idCollaborator") Long idCollaborator){

        CollaboratorEntity obj = this.collaboratorService.readById(idCollaborator);
        CollaboratorResponseDTO dto = this.collaboratorMapper.toGetResponseDTO(obj);

        dto.setIdLeader(obj.getLeader().getIdLeader());
        dto.setLeaderNames(obj.getLeader().getNames());

        dto.setIdRol(obj.getRol().getIdRol());
        dto.setRolDescription(obj.getRol().getDescription());

        dto.setIdRegion(obj.getRegion().getIdRegion());
        dto.setRegionDescription(obj.getRegion().getDescription());

        dto.setIdFunctionalLeader(obj.getFunctionalLeader().getIdFunctionalLeader());
        dto.setFunctionalLeaderNames(obj.getFunctionalLeader().getNames());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CollaboratorResponseDTO> save(@Validated @RequestBody CollaboratorRequestDTO requestDTO) {
        LeaderEntity leaderEntity =  leaderService.readById(requestDTO.getIdLeader());

        RolEntity rolEntity = rolService.readById(requestDTO.getIdRol());

        RegionEntity regionEntity = regionService.readById(requestDTO.getIdRegion());

        FunctionalLeaderEntity functionalLeaderEntity = functionalLeaderService.readById(requestDTO.getIdFunctionalLeader());

        CollaboratorEntity entidad = this.collaboratorMapper.toEntity(requestDTO);

        entidad.setLeader(leaderEntity);
        entidad.setRol(rolEntity);
        entidad.setRegion(regionEntity);
        entidad.setFunctionalLeader(functionalLeaderEntity);

        CollaboratorEntity entidadSave = this.collaboratorService.create( entidad);
        CollaboratorResponseDTO responseviaDTO = this.collaboratorMapper.toGetResponseDTO(entidadSave);
        return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{idLeader}")
    public ResponseEntity<CollaboratorResponseDTO> update(@Validated @PathVariable("idCollaborator") Long idCollaborator,
                                                    @RequestBody CollaboratorRequestDTO requestDTO){
        LeaderEntity leaderEntity =  leaderService.readById(requestDTO.getIdLeader());

        RolEntity rolEntity = rolService.readById(requestDTO.getIdRol());

        RegionEntity regionEntity = regionService.readById(requestDTO.getIdRegion());

        FunctionalLeaderEntity functionalLeaderEntity = functionalLeaderService.readById(requestDTO.getIdFunctionalLeader());

        CollaboratorEntity objEntitySource = this.collaboratorMapper.toEntity(requestDTO);

        objEntitySource.setLeader(leaderEntity);
        objEntitySource.setRol(rolEntity);
        objEntitySource.setRegion(regionEntity);
        objEntitySource.setFunctionalLeader(functionalLeaderEntity);

        CollaboratorEntity obj =  collaboratorService.update(objEntitySource, idCollaborator);
        CollaboratorResponseDTO responseviaDTO = this.collaboratorMapper.toGetResponseDTO(obj);
        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idCollaborator}")
    public ResponseEntity<CollaboratorResponseDTO> delete(@PathVariable("idCollaborator") Long idCollaborator){
        collaboratorService.deleteById(idCollaborator);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }


}
