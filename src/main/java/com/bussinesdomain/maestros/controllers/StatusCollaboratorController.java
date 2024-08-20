package com.bussinesdomain.maestros.controllers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.bussinesdomain.maestros.dto.StatusCollaboratorRequestDTO;
import com.bussinesdomain.maestros.dto.StatusCollaboratorResponseDTO;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.mapper.IStatusCollaboratorMapper;
import com.bussinesdomain.maestros.models.StatusCollaboratorEntity;
import com.bussinesdomain.maestros.services.IStatusCollaboratorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/statuscollaborator")
public class StatusCollaboratorController {
    private final IStatusCollaboratorMapper mapper;
	
	private final IStatusCollaboratorService service;
	
	// @PostMapping("/pagination")
	// public ResponseEntity<?> paginator(@RequestBody PaginationModel pagination ){
	// 	log.info("PAGINATION ..... " + pagination);
	// 	Page<StatusCollaboratorResponseDTO> lst = paginationCommons.pagination(pagination);
	// 	return new ResponseEntity<>(lst,HttpStatus.OK);
	// }
	
	
	@GetMapping("/all")
	public ResponseEntity<List<StatusCollaboratorResponseDTO>> findAll(){
		List<StatusCollaboratorEntity> statusCollaboratorEntities = service.getAll();
		List<StatusCollaboratorResponseDTO> unitsMeasureDTO = this.mapper.listEntityToDTO(statusCollaboratorEntities);
		return new ResponseEntity<>(unitsMeasureDTO,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StatusCollaboratorResponseDTO> findById(@PathVariable("id") Long id){
		StatusCollaboratorEntity StatusCollaboratorEntity = this.service.readById(id).orElseThrow(()->new ModelNotFoundException("ID NOT FOUND " + id)) ;
		StatusCollaboratorResponseDTO StatusCollaboratorDTO =this.mapper.toGetDTO(StatusCollaboratorEntity);
		return new ResponseEntity<>(StatusCollaboratorDTO,HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<StatusCollaboratorResponseDTO> save(@Validated @RequestBody  StatusCollaboratorRequestDTO dto){
		StatusCollaboratorEntity unitMesEntity = this.service.create( this.mapper.toEntity(dto) );
		StatusCollaboratorResponseDTO StatusCollaboratorDTO =  this.mapper.toGetDTO(unitMesEntity);
		return new ResponseEntity<>(StatusCollaboratorDTO,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<StatusCollaboratorResponseDTO> update(@Validated @PathVariable("id") Long id, @RequestBody StatusCollaboratorRequestDTO dto){
		dto.setIdStatusCollaborator(id);
		StatusCollaboratorEntity StatusCollaboratorEntity = this.mapper.toEntity(dto);
		StatusCollaboratorEntity StatusCollaboratorEntityUpdated = this.service.update(StatusCollaboratorEntity, id);
		return new ResponseEntity<>(this.mapper.toGetDTO(StatusCollaboratorEntityUpdated),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		this.service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}