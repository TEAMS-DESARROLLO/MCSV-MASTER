package com.bussinesdomain.maestros.controllers;

import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.*;
import com.bussinesdomain.maestros.mapper.ICommunityMapper;
import com.bussinesdomain.maestros.mapper.ILeaderMapper;
import com.bussinesdomain.maestros.models.CommunityEntity;
import com.bussinesdomain.maestros.models.LeaderEntity;
import com.bussinesdomain.maestros.services.ICommunityService;
import com.bussinesdomain.maestros.services.ILeaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/leader")
public class LeaderController {

    private final ILeaderMapper leaderMapper;
    private final ILeaderService leaderService;

    private final ICommunityService communityService;

    private final IPaginationCommons<LeaderDTO> iPaginationCommons;

    @PostMapping("/pagination")
    public ResponseEntity<?> paginador(@RequestBody PaginationModel pagination ){
        Page<LeaderDTO> lst = iPaginationCommons.pagination(pagination);
        return new ResponseEntity<>(lst, HttpStatus.OK) ;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LeaderDTO>> findByFiltro(){

        List<LeaderEntity> objs = leaderService.getAll();
        List<LeaderDTO> lst = leaderMapper.listEntityToDTO(objs);
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{idLeader}")
    public ResponseEntity<LeaderResponseDTO> findById(@PathVariable("idLeader") Long idLeader){

        LeaderEntity obj = this.leaderService.readById(idLeader);
        LeaderResponseDTO dto = this.leaderMapper.toGetResponseDTO(obj);
        dto.setIdCommunity(obj.getCommunity().getIdCommunity());
        dto.setCommunityDescription(obj.getCommunity().getDescription() );
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<LeaderResponseDTO> save(@Validated @RequestBody LeaderRequestDTO requestDTO) {
        CommunityEntity communityEntity =  communityService.readById(requestDTO.getIdCommunity());

        LeaderEntity entidad = this.leaderMapper.toEntity(requestDTO);
        entidad.setCommunity(communityEntity);
        LeaderEntity entidadSave = this.leaderService.create( entidad);
        LeaderResponseDTO responseviaDTO = this.leaderMapper.toGetResponseDTO(entidadSave);
        return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{idLeader}")
    public ResponseEntity<LeaderResponseDTO> update(@Validated @PathVariable("idLeader") Long idLeader,
                                                       @RequestBody LeaderRequestDTO requestDTO){
        CommunityEntity communityEntity =  communityService.readById(requestDTO.getIdCommunity());
        LeaderEntity objEntitySource = this.leaderMapper.toEntity(requestDTO);
        objEntitySource.setCommunity(communityEntity);
        LeaderEntity obj =  leaderService.update(objEntitySource, idLeader);
        LeaderResponseDTO responseviaDTO = this.leaderMapper.toGetResponseDTO(obj);
        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idLeader}")
    public ResponseEntity<CommunityResponseDTO> delete(@PathVariable("idLeader") Long idLeader){
        leaderService.deleteById(idLeader);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
