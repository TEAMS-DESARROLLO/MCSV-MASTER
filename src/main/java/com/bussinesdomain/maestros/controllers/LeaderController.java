package com.bussinesdomain.maestros.controllers;


import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.*;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.mapper.ILeaderMapper;
import com.bussinesdomain.maestros.models.PracticeEntity;
import com.bussinesdomain.maestros.models.LeaderEntity;
import com.bussinesdomain.maestros.services.IPracticeService;
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

    private final IPracticeService practiceService;

    private final LeaderPaginationService leaderPaginationService;

    @PostMapping("/pagination")
    public ResponseEntity<?> paginador(@RequestBody PaginationModel pagination ){
        Page<LeaderDTO> lst = leaderPaginationService.pagination(pagination);
        return new ResponseEntity<>(lst, HttpStatus.OK) ;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LeaderDTO>> findByFiltro(){

        List<LeaderDTO> lst = leaderService.getAll().stream().map(obj -> {
            LeaderDTO ele = leaderMapper.toGetDTO(obj);
            ele.setIdPractice(obj.getPractice().getIdPractice());
            ele.setPracticeDescription(obj.getPractice().getDescription());
            return ele;
        }).collect(Collectors.toList());
        
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{idLeader}")
    public ResponseEntity<LeaderResponseDTO> findById(@PathVariable("idLeader") Long idLeader){

        LeaderEntity obj = this.leaderService.readById(idLeader).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);

        if(obj==null){
            throw new  ModelNotFoundException("No se encontro el registro solicitado, o status no es activo");
        }


        LeaderResponseDTO dto = this.leaderMapper.toGetResponseDTO(obj);
        dto.setIdPractice(obj.getPractice().getIdPractice());
        dto.setPracticeDescription(obj.getPractice().getDescription() );
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<LeaderResponseDTO> save(@Validated @RequestBody LeaderRequestDTO requestDTO) {
        PracticeEntity practiceEntity =  practiceService.readById(requestDTO.getIdPractice()).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null); 

        LeaderEntity entidad = this.leaderMapper.toEntity(requestDTO);
        entidad.setPractice(practiceEntity);
        LeaderEntity entidadSave = this.leaderService.create( entidad);
        LeaderResponseDTO leaderResponseDTO = this.leaderMapper.toGetResponseDTO(entidadSave);
        leaderResponseDTO.setIdPractice(entidadSave.getPractice().getIdPractice());
        leaderResponseDTO.setPracticeDescription(entidadSave.getPractice().getDescription());
        return new ResponseEntity<>(leaderResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{idLeader}")
    public ResponseEntity<LeaderResponseDTO> update(@Validated @PathVariable("idLeader") Long idLeader,
                                                       @RequestBody LeaderRequestDTO requestDTO){
        PracticeEntity practiceEntity =  practiceService.readById(requestDTO.getIdPractice()).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);
        LeaderEntity objEntitySource = this.leaderMapper.toEntity(requestDTO);
        objEntitySource.setPractice(practiceEntity);
        LeaderEntity obj =  leaderService.update(objEntitySource, idLeader);
        LeaderResponseDTO responseviaDTO = this.leaderMapper.toGetResponseDTO(obj);
        responseviaDTO.setIdPractice(obj.getPractice().getIdPractice());
        responseviaDTO.setPracticeDescription(obj.getPractice().getDescription());
        return new ResponseEntity<>(responseviaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{idLeader}")
    public ResponseEntity<PracticeResponseDTO> delete(@PathVariable("idLeader") Long idLeader){
        leaderService.deleteById(idLeader);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @PostMapping("/leadersByIds")
    public ResponseEntity<List<LeaderResponseDTO>> communitiesByIds(@RequestParam("ids") List<Long> ids) {
        
        List<LeaderResponseDTO> lista = leaderService.leadersByIds(ids);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }    
}
