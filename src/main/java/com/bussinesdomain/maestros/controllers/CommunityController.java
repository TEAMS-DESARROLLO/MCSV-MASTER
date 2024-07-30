package com.bussinesdomain.maestros.controllers;

import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.*;
import com.bussinesdomain.maestros.mapper.ICommunityMapper;
import com.bussinesdomain.maestros.models.CommunityEntity;
import com.bussinesdomain.maestros.services.ICommunityService;
import com.bussinesdomain.maestros.services.impl.CommunityPaginationService;

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
@RequestMapping("/community")
public class CommunityController {

    private final ICommunityMapper communityMapper;
    private final ICommunityService communityService;
    private final CommunityPaginationService communityPaginationService;



    @PostMapping("/pagination")
    public ResponseEntity<Page<CommunityDTO> > paginador(@RequestBody PaginationModel pagination ){
        Page<CommunityDTO> lst = communityPaginationService.pagination(pagination);
        return new ResponseEntity<>(lst, HttpStatus.OK) ;
    }    

    @GetMapping("/all")
    public ResponseEntity<List<CommunityResponseDTO>> findByFiltro(){

         List<CommunityEntity> objs = communityService.getAll();
         List<CommunityResponseDTO> lst = communityMapper.listEntityToResponseDTO(objs);
         return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{idCommunity}")
    public ResponseEntity<CommunityResponseDTO> findById(@PathVariable("idCommunity") Long idCommunity){

        CommunityEntity obj = this.communityService.readById(idCommunity).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null); 
        CommunityResponseDTO dto = this.communityMapper.toGetResponseDTO(obj);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CommunityResponseDTO> save(@Validated @RequestBody CommunityRequestDTO requestDTO) {
        CommunityEntity entidad = this.communityMapper.toEntity(requestDTO);
        CommunityEntity entidadSave = this.communityService.create( entidad);
        CommunityResponseDTO responseviaDTO = this.communityMapper.toGetResponseDTO(entidadSave);
        return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
    }


    @PutMapping("/{idCommunity}")
    public ResponseEntity<CommunityResponseDTO> update(@Validated @PathVariable("idCommunity") Long idCommunity,
                                                       @RequestBody CommunityRequestDTO requestDTO){
        CommunityEntity objEntitySource = this.communityMapper.toEntity(requestDTO);
        CommunityEntity obj =  communityService.update(objEntitySource, idCommunity);
        CommunityResponseDTO responseviaDTO = this.communityMapper.toGetResponseDTO(obj);
        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{idCommunity}")
    public ResponseEntity<CommunityResponseDTO> delete(@PathVariable("idCommunity") Long idCommunity){
        communityService.deleteById(idCommunity);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
