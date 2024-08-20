package com.bussinesdomain.maestros.controllers;

import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.*;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.mapper.IFunctionalLeaderMapper;
import com.bussinesdomain.maestros.models.FunctionalLeaderEntity;
import com.bussinesdomain.maestros.services.IFunctionalLeaderService;
import com.bussinesdomain.maestros.services.impl.FunctionalLeaderPaginationService;

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
@RequestMapping("/functionalLeader")
public class FunctionalLeaderController {

    private final IFunctionalLeaderService functionalLeaderService;
    private final IFunctionalLeaderMapper functionalLeaderMapper;
    private final FunctionalLeaderPaginationService functionalLeaderPaginationService;

    @PostMapping("/pagination")
    public ResponseEntity<?> paginador(@RequestBody PaginationModel pagination ){
        Page<FunctionalLeaderDTO> lst = functionalLeaderPaginationService.pagination(pagination);
        return new ResponseEntity<>(lst, HttpStatus.OK) ;
    }       

    @GetMapping("/all")
    public ResponseEntity<List<FunctionalLeaderDTO>> findByFiltro(){

        List<FunctionalLeaderEntity> objs = functionalLeaderService.getAll();
        List<FunctionalLeaderDTO> lst = functionalLeaderMapper.listEntityToDTO(objs);
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }


    @GetMapping("/{idFunctionalLeader}")
    public ResponseEntity<FunctionalLeaderResponseDTO> findById(@PathVariable("idFunctionalLeader") Long idFunctionalLeader){

        FunctionalLeaderEntity obj = this.functionalLeaderService.readById(idFunctionalLeader).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null); 
        FunctionalLeaderResponseDTO dto = this.functionalLeaderMapper.toGetResponseDTO(obj);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<FunctionalLeaderResponseDTO> save(@Validated @RequestBody FunctionalLeaderRequestDTO requestDTO) {

        FunctionalLeaderEntity entidad = this.functionalLeaderMapper.toEntity(requestDTO);
        FunctionalLeaderEntity entidadSave = this.functionalLeaderService.create( entidad);
        FunctionalLeaderResponseDTO responseviaDTO = this.functionalLeaderMapper.toGetResponseDTO(entidadSave);
        return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{idFunctionalLeader}")
    public ResponseEntity<FunctionalLeaderResponseDTO> update(@Validated @PathVariable("idFunctionalLeader") Long idFunctionalLeader,
                                                    @RequestBody FunctionalLeaderRequestDTO requestDTO){
        FunctionalLeaderEntity objEntitySource = this.functionalLeaderMapper.toEntity(requestDTO);
        FunctionalLeaderEntity obj =  functionalLeaderService.update(objEntitySource, idFunctionalLeader);

        if(obj==null){
            throw new  ModelNotFoundException("No se encontro el registro solicitado, o status no es activo");
        }


        FunctionalLeaderResponseDTO responseviaDTO = this.functionalLeaderMapper.toGetResponseDTO(obj);
        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idFunctionalLeader}")
    public ResponseEntity<PracticeResponseDTO> delete(@PathVariable("idFunctionalLeader") Long idFunctionalLeader){
        functionalLeaderService.deleteById(idFunctionalLeader);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
