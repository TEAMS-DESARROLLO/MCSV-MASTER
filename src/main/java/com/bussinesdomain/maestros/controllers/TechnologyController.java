package com.bussinesdomain.maestros.controllers;

import com.bussinesdomain.maestros.dto.PracticeResponseDTO;
import com.bussinesdomain.maestros.dto.TechnologyDTO;
import com.bussinesdomain.maestros.dto.TechnologyRequestDTO;
import com.bussinesdomain.maestros.dto.TechnologyResponseDTO;
import com.bussinesdomain.maestros.mapper.ITechnologyMapper;
import com.bussinesdomain.maestros.models.TechnologyEntity;
import com.bussinesdomain.maestros.services.ITechnologyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/technology")
public class TechnologyController {

    private final ITechnologyService technologyService;
    private final ITechnologyMapper technologyMapper;

    @GetMapping("/all")
    public ResponseEntity<List<TechnologyDTO>> findByFiltro(){

        List<TechnologyEntity> objs = technologyService.getAll();
        List<TechnologyDTO> lst = technologyMapper.listEntityToDTO(objs);
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }


    @GetMapping("/{idTechnology}")
    public ResponseEntity<TechnologyResponseDTO> findById(@PathVariable("idTechnology") Long idTechnology){

        TechnologyEntity obj = this.technologyService.readById(idTechnology).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);
        TechnologyResponseDTO dto = this.technologyMapper.toGetResponseDTO(obj);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TechnologyResponseDTO> save(@Validated @RequestBody TechnologyRequestDTO requestDTO) {

        TechnologyEntity entidad = this.technologyMapper.toEntity(requestDTO);
        TechnologyEntity entidadSave = this.technologyService.create( entidad);
        TechnologyResponseDTO responseviaDTO = this.technologyMapper.toGetResponseDTO(entidadSave);
        return new ResponseEntity<>(responseviaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{idTechnology}")
    public ResponseEntity<TechnologyResponseDTO> update(@Validated @PathVariable("idTechnology") Long idTechnology,
                                                    @RequestBody TechnologyRequestDTO requestDTO){
        TechnologyEntity objEntitySource = this.technologyMapper.toEntity(requestDTO);
        TechnologyEntity obj =  technologyService.update(objEntitySource, idTechnology);
        TechnologyResponseDTO responseviaDTO = this.technologyMapper.toGetResponseDTO(obj);
        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idTechnology}")
    public ResponseEntity<PracticeResponseDTO> delete(@PathVariable("idTechnology") Long idTechnology){
        technologyService.deleteById(idTechnology);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
