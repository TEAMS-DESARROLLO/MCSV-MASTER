package com.bussinesdomain.maestros.controllers;

import java.util.List;

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
import com.bussinesdomain.maestros.dto.FunctionalLeaderDTO;
import com.bussinesdomain.maestros.dto.FunctionalLeaderRequestDTO;
import com.bussinesdomain.maestros.dto.FunctionalLeaderResponseDTO;
import com.bussinesdomain.maestros.mapper.IFunctionalLeaderMapper;
import com.bussinesdomain.maestros.models.FunctionalLeaderEntity;
import com.bussinesdomain.maestros.services.IFunctionalLeaderService;
import com.bussinesdomain.maestros.services.impl.FunctionalLeaderPaginationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
        Long idUser = (Long) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        entidad.setIdUser(idUser);
        FunctionalLeaderEntity entidadSave = this.functionalLeaderService.create( entidad);
        FunctionalLeaderResponseDTO responseviaDTO = this.functionalLeaderMapper.toGetResponseDTO(entidadSave);
        return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{idFunctionalLeader}")
    public ResponseEntity<FunctionalLeaderResponseDTO> update(@Validated @PathVariable("idFunctionalLeader") Long idFunctionalLeader,
                                                    @RequestBody FunctionalLeaderRequestDTO requestDTO){
        FunctionalLeaderEntity objEntitySource = this.functionalLeaderMapper.toEntity(requestDTO);
        Long idUser = (Long) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        objEntitySource.setIdUser(idUser);
        FunctionalLeaderEntity obj =  functionalLeaderService.update(objEntitySource, idFunctionalLeader);
        FunctionalLeaderResponseDTO responseviaDTO = this.functionalLeaderMapper.toGetResponseDTO(obj);
        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idFunctionalLeader}")
    public ResponseEntity<CommunityResponseDTO> delete(@PathVariable("idFunctionalLeader") Long idFunctionalLeader){
        functionalLeaderService.deleteById(idFunctionalLeader);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
