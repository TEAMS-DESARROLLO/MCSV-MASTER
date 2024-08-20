package com.bussinesdomain.maestros.controllers;

import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.config.ConfigToken;
import com.bussinesdomain.maestros.dto.*;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.mapper.IPracticeMapper;
import com.bussinesdomain.maestros.models.PracticeEntity;
import com.bussinesdomain.maestros.services.IPracticeService;
import com.bussinesdomain.maestros.services.impl.PracticePaginationService;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class PracticeController {

    private final IPracticeMapper communityMapper;
    private final IPracticeService communityService;
    private final PracticePaginationService communityPaginationService;



    @PostMapping("/pagination")
    public ResponseEntity<Page<PracticeDTO> > paginador(@RequestBody PaginationModel pagination ){
        Page<PracticeDTO> lst = communityPaginationService.pagination(pagination);
        return new ResponseEntity<>(lst, HttpStatus.OK) ;
    }    

    @GetMapping("/all")
    public ResponseEntity<List<PracticeResponseDTO>> findByFiltro(){

        String token = ConfigToken.tokenBack;
        log.info("Token: " + token);

         List<PracticeEntity> objs = communityService.getAll();
         List<PracticeResponseDTO> lst = communityMapper.listEntityToResponseDTO(objs);
         return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{idPractice}")
    public ResponseEntity<PracticeResponseDTO> findById(@PathVariable("idPractice") Long idPractice){

        PracticeEntity obj = this.communityService.readById(idPractice).stream(). filter(p-> Objects.nonNull(p.getRegistrationStatus())).filter(p->p.getRegistrationStatus().equals("A") ).findFirst().orElse(null); 
        //CommunityEntity obj2 = obj.stream(). filter(p-> Objects.nonNull(p.getRegistrationStatus())). filter(p->p.getRegistrationStatus().equals("A") ).findFirst().orElse(null); 
        if(obj==null){
            throw new  ModelNotFoundException("No se encontro el registro solicitado, o status no es activo");
        }

        PracticeResponseDTO dto = this.communityMapper.toGetResponseDTO(obj);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PracticeResponseDTO> save(@Validated @RequestBody PracticeRequestDTO requestDTO) {

        Long idUsuario = (Long) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        PracticeEntity entidad = this.communityMapper.toEntity(requestDTO);
        entidad.setIdUser(idUsuario);

        PracticeEntity entidadSave = this.communityService.create( entidad);
        PracticeResponseDTO responseviaDTO = this.communityMapper.toGetResponseDTO(entidadSave);
        return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
    }


    @PutMapping("/{idCommunity}")
    public ResponseEntity<PracticeResponseDTO> update(@Validated @PathVariable("idCommunity") Long idCommunity,
                                                       @RequestBody PracticeRequestDTO requestDTO){

        Long idUsuario = (Long) SecurityContextHolder.getContext().getAuthentication().getCredentials();

        PracticeEntity objEntitySource = this.communityMapper.toEntity(requestDTO);
        PracticeEntity obj =  communityService.update(objEntitySource, idCommunity);
        obj.setIdUser(idUsuario);
        
        PracticeResponseDTO responseviaDTO = this.communityMapper.toGetResponseDTO(obj);
        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{idCommunity}")
    public ResponseEntity<PracticeResponseDTO> delete(@PathVariable("idCommunity") Long idCommunity){
        communityService.deleteById(idCommunity);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PostMapping("/communityByIds")
    public ResponseEntity<List<PracticeResponseDTO>> communitiesByIds(List<Long> ids) {
        
        List<PracticeResponseDTO> lista = communityService.practicesByIds(ids);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }



}
