package com.bussinesdomain.maestros.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
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

import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.RolRequestDTO;
import com.bussinesdomain.maestros.dto.RolResponseDTO;
import com.bussinesdomain.maestros.exception.ModelNotFoundException;
import com.bussinesdomain.maestros.mapper.IRolMapper;
import com.bussinesdomain.maestros.models.RolEntity;
import com.bussinesdomain.maestros.services.IRolService;
import com.bussinesdomain.maestros.services.impl.RolPaginationServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rol")
public class RolController {

	private final IRolMapper mapper;
	private final RolPaginationServiceImpl rolPaginationServiceImpl;
	private final IRolService service;
	
	@PostMapping("/pagination")
	public ResponseEntity<?> paginator(@RequestBody PaginationModel pagination){
		log.info("PAGINATION ..... " + pagination);
		Page<RolResponseDTO> lst = rolPaginationServiceImpl.pagination(pagination);
		return new ResponseEntity<>(lst,HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<RolResponseDTO>> findAll(){
		List<RolEntity> rolesEntities = this.service.getAll();
		List<RolResponseDTO> rolesDTO = this.mapper.listEntityToDTO(rolesEntities);
		return new ResponseEntity<>(rolesDTO,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RolResponseDTO> findById(@PathVariable("id")Long id){
		RolEntity rolEntity = this.service.readById(id).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);

        if(rolEntity==null){
            throw new  ModelNotFoundException("No se encontro el registro solicitado, o status no es activo");
        }

		RolResponseDTO rolDTO = this.mapper.toGetDTO(rolEntity);
		return new ResponseEntity<>(rolDTO,HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<RolResponseDTO> save(@Validated @RequestBody RolRequestDTO dto){
		RolEntity rolEntity = this.service.create( this.mapper.toEntity(dto) );
		RolResponseDTO rolDTO = this.mapper.toGetDTO(rolEntity);
		return new ResponseEntity<>(rolDTO,HttpStatus.CREATED);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RolResponseDTO> update(@Validated @PathVariable("id") Long id,@RequestBody RolRequestDTO dto){
		dto.setIdRol(id);
		RolEntity roEntity = this.mapper.toEntity(dto);
		RolEntity rolEntityUpdated = this.service.update(roEntity, id);
		return new ResponseEntity<>(this.mapper.toGetDTO(rolEntityUpdated),HttpStatus.OK);	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id ){
		this.service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
