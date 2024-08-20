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

import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.UnitMeasureRequestDTO;
import com.bussinesdomain.maestros.dto.UnitMeasureResponseDTO;
import com.bussinesdomain.maestros.mapper.IUnitMeasureMapper;
import com.bussinesdomain.maestros.models.UnitMeasureEntity;
import com.bussinesdomain.maestros.services.IUnitMeasureService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/unitmeasure")
public class UnitMeasureController {
	
	private final IUnitMeasureMapper mapper;
	private final IPaginationCommons<UnitMeasureResponseDTO> paginationCommons; 
	private final IUnitMeasureService service;
	
	@PostMapping("/pagination")
	public ResponseEntity<?> paginator(@RequestBody PaginationModel pagination ){
		log.info("PAGINATION ..... " + pagination);
		Page<UnitMeasureResponseDTO> lst = paginationCommons.pagination(pagination);
		return new ResponseEntity<>(lst,HttpStatus.OK);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<UnitMeasureResponseDTO>> findAll(){
		List<UnitMeasureEntity> unitsMeasureEntities = this.service.getAll();
		List<UnitMeasureResponseDTO> unitsMeasureDTO = this.mapper.listEntityToDTO(unitsMeasureEntities);
		return new ResponseEntity<>(unitsMeasureDTO,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UnitMeasureResponseDTO> findById(@PathVariable("id") Long id){
		UnitMeasureEntity unitMeasureEntity = this.service.readById(id).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);
		UnitMeasureResponseDTO unitMeasureDTO =this.mapper.toGetDTO(unitMeasureEntity);
		return new ResponseEntity<>(unitMeasureDTO,HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<UnitMeasureResponseDTO> save(@Validated @RequestBody  UnitMeasureRequestDTO dto){
		UnitMeasureEntity unitMesEntity = this.service.create( this.mapper.toEntity(dto) );
		UnitMeasureResponseDTO unitMeasureDTO =  this.mapper.toGetDTO(unitMesEntity);
		return new ResponseEntity<>(unitMeasureDTO,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UnitMeasureResponseDTO> update(@Validated @PathVariable("id") Long id, @RequestBody UnitMeasureRequestDTO dto){
		dto.setIdUnitMeasure(id);
		UnitMeasureEntity unitMeasureEntity = this.mapper.toEntity(dto);
		UnitMeasureEntity unitMeasureEntityUpdated = this.service.update(unitMeasureEntity, id);
		return new ResponseEntity<>(this.mapper.toGetDTO(unitMeasureEntityUpdated),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		this.service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
