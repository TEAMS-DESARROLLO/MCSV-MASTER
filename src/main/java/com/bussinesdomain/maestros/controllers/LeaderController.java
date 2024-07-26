package com.bussinesdomain.maestros.controllers;

import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.*;
import com.bussinesdomain.maestros.mapper.ILeaderMapper;
import com.bussinesdomain.maestros.models.CommunityEntity;
import com.bussinesdomain.maestros.models.LeaderEntity;
import com.bussinesdomain.maestros.services.ICommunityService;
import com.bussinesdomain.maestros.services.ILeaderService;
import com.bussinesdomain.maestros.services.impl.LeaderPaginationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/leader")
public class LeaderController {

    private final ILeaderMapper leaderMapper;
    private final ILeaderService leaderService;

    private final ICommunityService communityService;

    private final LeaderPaginationService leaderPaginationService;

    @PostMapping("/pagination")
    public ResponseEntity<?> paginador(@RequestBody PaginationModel pagination ){
        Page<LeaderDTO> lst = leaderPaginationService.pagination(pagination);
        return new ResponseEntity<>(lst, HttpStatus.OK) ;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LeaderDTO>> findByFiltro(){

        List<LeaderEntity> objs = leaderService.getAll();
        List<LeaderDTO> lst = objs.stream().map( obj -> {
            LeaderDTO ele = leaderMapper.toGetDTO(obj);
            ele.setIdCommunity(obj.getCommunity().getIdCommunity());
            ele.setCommunityDescription(obj.getCommunity().getDescription());
            return ele;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{idLeader}")
    public ResponseEntity<LeaderResponseDTO> findById(@PathVariable("idLeader") Long idLeader){

        LeaderEntity obj = this.leaderService.readById(idLeader).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);
        LeaderResponseDTO dto = this.leaderMapper.toGetResponseDTO(obj);
        dto.setIdCommunity(obj.getCommunity().getIdCommunity());
        dto.setCommunityDescription(obj.getCommunity().getDescription() );
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<LeaderResponseDTO> save(@Validated @RequestBody LeaderRequestDTO requestDTO) {
        CommunityEntity communityEntity =  communityService.readById(requestDTO.getIdCommunity()).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null); 

        LeaderEntity entidad = this.leaderMapper.toEntity(requestDTO);
        entidad.setCommunity(communityEntity);
        LeaderEntity entidadSave = this.leaderService.create( entidad);
        LeaderResponseDTO responseviaDTO = this.leaderMapper.toGetResponseDTO(entidadSave);
        responseviaDTO.setIdCommunity(entidadSave.getCommunity().getIdCommunity());
        responseviaDTO.setCommunityDescription(entidadSave.getCommunity().getDescription());
        return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{idLeader}")
    public ResponseEntity<LeaderResponseDTO> update(@Validated @PathVariable("idLeader") Long idLeader,
                                                       @RequestBody LeaderRequestDTO requestDTO){
        CommunityEntity communityEntity =  communityService.readById(requestDTO.getIdCommunity()).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);
        LeaderEntity objEntitySource = this.leaderMapper.toEntity(requestDTO);
        objEntitySource.setCommunity(communityEntity);
        LeaderEntity obj =  leaderService.update(objEntitySource, idLeader);
        LeaderResponseDTO responseviaDTO = this.leaderMapper.toGetResponseDTO(obj);
        responseviaDTO.setIdCommunity(obj.getCommunity().getIdCommunity());
        responseviaDTO.setCommunityDescription(obj.getCommunity().getDescription());
        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idLeader}")
    public ResponseEntity<CommunityResponseDTO> delete(@PathVariable("idLeader") Long idLeader){
        leaderService.deleteById(idLeader);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
