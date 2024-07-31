package com.bussinesdomain.maestros.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.CommunityResponseDTO;
import com.bussinesdomain.maestros.dto.LeaderDTO;
import com.bussinesdomain.maestros.dto.LeaderRequestDTO;
import com.bussinesdomain.maestros.dto.LeaderResponseDTO;
import com.bussinesdomain.maestros.mapper.ILeaderMapper;
import com.bussinesdomain.maestros.models.CommunityEntity;
import com.bussinesdomain.maestros.models.LeaderEntity;
import com.bussinesdomain.maestros.services.ICommunityService;
import com.bussinesdomain.maestros.services.ILeaderService;
import com.bussinesdomain.maestros.services.impl.LeaderPaginationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
        Long idUser = (Long) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        entidad.setIdUser(idUser);
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
        Long idUser = (Long) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        objEntitySource.setIdUser(idUser);
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
