package com.bussinesdomain.maestros.controllers;

import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.CommunityResponseDTO;
import com.bussinesdomain.maestros.dto.SubpracticaDTO;
import com.bussinesdomain.maestros.dto.TechnologyDTO;
import com.bussinesdomain.maestros.dto.TechnologyRequestDTO;
import com.bussinesdomain.maestros.dto.TechnologyResponseDTO;
import com.bussinesdomain.maestros.mapper.ITechnologyMapper;
import com.bussinesdomain.maestros.models.SubpracticaEntity;
import com.bussinesdomain.maestros.models.TechnologyEntity;
import com.bussinesdomain.maestros.services.ISubPracticaService;
import com.bussinesdomain.maestros.services.ITechnologyService;
import com.bussinesdomain.maestros.services.impl.SubpracticaPaginationService;
import com.bussinesdomain.maestros.services.impl.TechnologyPaginationService;

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
@RequestMapping("/technology")
public class TechnologyController {

    private final ITechnologyService technologyService;
    private final ITechnologyMapper technologyMapper;
    private final ISubPracticaService SubpracticaService;
    private final TechnologyPaginationService technologyPaginationService;

    @PostMapping("/pagination")
    public ResponseEntity<?> paginador(@RequestBody PaginationModel pagination ){
        Page<TechnologyDTO> lst = technologyPaginationService.pagination(pagination);
        return new ResponseEntity<>(lst, HttpStatus.OK) ;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TechnologyDTO>> findByFiltro(){

        List<TechnologyEntity> objs = technologyService.getAll();
        List<TechnologyDTO> lst = objs.stream().map(x -> {
            TechnologyDTO dto = technologyMapper.toGetDTO(x);
            dto.setIdSubpractica(x.getSubpracticaEntity().getIdSubpractica());
            dto.setDescriptionSubpractica(x.getSubpracticaEntity().getDescriptionSubpractica());
            return dto;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }


    @GetMapping("/{idTechnology}")
    public ResponseEntity<TechnologyResponseDTO> findById(@PathVariable("idTechnology") Long idTechnology){

        TechnologyEntity obj = this.technologyService.readById(idTechnology);
        TechnologyResponseDTO dto = this.technologyMapper.toGetResponseDTO(obj);
        dto.setIdSubpractica(obj.getSubpracticaEntity().getIdSubpractica());
        dto.setDescriptionSubpractica(obj.getSubpracticaEntity().getDescriptionSubpractica());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TechnologyResponseDTO> save(@Validated @RequestBody TechnologyRequestDTO requestDTO) {

        TechnologyEntity entidad = this.technologyMapper.toEntity(requestDTO);
        SubpracticaEntity subpracticaEntity = this.SubpracticaService.readById(requestDTO.getIdSubpractica());
        entidad.setSubpracticaEntity(subpracticaEntity);
        TechnologyEntity entidadSave = this.technologyService.create(entidad);
        TechnologyResponseDTO responseviaDTO = this.technologyMapper.toGetResponseDTO(entidadSave);
        responseviaDTO.setIdSubpractica(entidadSave.getSubpracticaEntity().getIdSubpractica());
        responseviaDTO.setDescriptionSubpractica(entidadSave.getSubpracticaEntity().getDescriptionSubpractica());
        return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{idTechnology}")
    public ResponseEntity<TechnologyResponseDTO> update(@Validated @PathVariable("idTechnology") Long idTechnology,
                                                    @RequestBody TechnologyRequestDTO requestDTO){
        TechnologyEntity objEntitySource = this.technologyMapper.toEntity(requestDTO);
        SubpracticaEntity subpracticaEntity = this.SubpracticaService.readById(requestDTO.getIdSubpractica());
        objEntitySource.setSubpracticaEntity(subpracticaEntity);
        TechnologyEntity obj =  technologyService.update(objEntitySource, idTechnology);
        TechnologyResponseDTO responseviaDTO = this.technologyMapper.toGetResponseDTO(obj);
        responseviaDTO.setIdSubpractica(obj.getSubpracticaEntity().getIdSubpractica());
        responseviaDTO.setDescriptionSubpractica(obj.getSubpracticaEntity().getDescriptionSubpractica());
        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idTechnology}")
    public ResponseEntity<CommunityResponseDTO> delete(@PathVariable("idTechnology") Long idTechnology){
        technologyService.deleteById(idTechnology);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
