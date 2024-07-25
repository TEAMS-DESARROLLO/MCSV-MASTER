package com.bussinesdomain.maestros.controllers;

import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.*;
import com.bussinesdomain.maestros.mapper.ISubpracticaMapper;
import com.bussinesdomain.maestros.models.CommunityEntity;
import com.bussinesdomain.maestros.models.SubpracticaEntity;
import com.bussinesdomain.maestros.services.ICommunityService;
import com.bussinesdomain.maestros.services.ISubPracticaService;
import com.bussinesdomain.maestros.services.impl.SubpracticaPaginationService;

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
@RequestMapping("/Subpractica")
public class SubpracticaController {

    private final ISubpracticaMapper SubpracticaMapper;
    private final ISubPracticaService SubpracticaService;

    private final ICommunityService communityService;

    private final SubpracticaPaginationService subpracticaPaginationService;

    @PostMapping("/pagination")
    public ResponseEntity<?> paginador(@RequestBody PaginationModel pagination ){
        Page<SubpracticaDTO> lst = subpracticaPaginationService.pagination(pagination);
        return new ResponseEntity<>(lst, HttpStatus.OK) ;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubpracticaDTO>> findByFiltro(){

        List<SubpracticaEntity> objs = SubpracticaService.getAll();
        List<SubpracticaDTO> lst = objs.stream().map( obj -> {
            SubpracticaDTO ele = SubpracticaMapper.toGetDTO(obj);
            ele.setIdCommunity(obj.getComunidadEntity().getIdCommunity());
            ele.setCommunityDescription(obj.getComunidadEntity().getDescription());
            return ele;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{idSubpractica}")
    public ResponseEntity<SubpracticaResponseDTO> findById(@PathVariable("idSubpractica") Long idSubpractica){

        SubpracticaEntity obj = this.SubpracticaService.readById(idSubpractica);
        SubpracticaResponseDTO dto = this.SubpracticaMapper.toGetResponseDTO(obj);
        dto.setIdCommunity(obj.getComunidadEntity().getIdCommunity());
        dto.setCommunityDescription(obj.getComunidadEntity().getDescription() );
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SubpracticaResponseDTO> save(@Validated @RequestBody SubpracticaRequestDTO requestDTO) {
        CommunityEntity communityEntity =  communityService.readById(requestDTO.getIdCommunity());

        SubpracticaEntity entidad = this.SubpracticaMapper.toEntity(requestDTO);
        entidad.setComunidadEntity(communityEntity);
        SubpracticaEntity entidadSave = this.SubpracticaService.create( entidad);
        SubpracticaResponseDTO responseviaDTO = this.SubpracticaMapper.toGetResponseDTO(entidadSave);
        responseviaDTO.setIdCommunity(entidadSave.getComunidadEntity().getIdCommunity());
        responseviaDTO.setCommunityDescription(entidadSave.getComunidadEntity().getDescription());
        return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{idSubpractica}")
    public ResponseEntity<SubpracticaResponseDTO> update(@Validated @PathVariable("idSubpractica") Long idSubpractica,
                                                       @RequestBody SubpracticaRequestDTO requestDTO){
        CommunityEntity communityEntity =  communityService.readById(requestDTO.getIdCommunity());
        SubpracticaEntity objEntitySource = this.SubpracticaMapper.toEntity(requestDTO);
        objEntitySource.setComunidadEntity(communityEntity);
        SubpracticaEntity obj =  SubpracticaService.update(objEntitySource, idSubpractica);
        SubpracticaResponseDTO responseviaDTO = this.SubpracticaMapper.toGetResponseDTO(obj);
        responseviaDTO.setIdCommunity(obj.getComunidadEntity().getIdCommunity());
        responseviaDTO.setCommunityDescription(obj.getComunidadEntity().getDescription());
        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idSubpractica}")
    public ResponseEntity<CommunityResponseDTO> delete(@PathVariable("idSubpractica") Long idSubpractica){
        SubpracticaService.deleteById(idSubpractica);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
