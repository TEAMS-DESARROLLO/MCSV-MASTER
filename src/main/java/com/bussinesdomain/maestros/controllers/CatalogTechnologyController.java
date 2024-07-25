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
import com.bussinesdomain.maestros.dto.CatalogTechnologyRequestDTO;
import com.bussinesdomain.maestros.dto.CatalogTechnologyResponseDTO;
import com.bussinesdomain.maestros.mapper.ICatalogTechnologyMapper;
import com.bussinesdomain.maestros.models.CatalogTechnologyEntity;
import com.bussinesdomain.maestros.services.ICatalogTechnologyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/catalogtechnology")

public class CatalogTechnologyController {
    private final ICatalogTechnologyMapper mapper;
	private final IPaginationCommons<CatalogTechnologyResponseDTO> paginationCommons; 
	private final ICatalogTechnologyService service;
	
	@PostMapping("/pagination")
	public ResponseEntity<?> paginator(@RequestBody PaginationModel pagination ){
		log.info("PAGINATION ..... " + pagination);
		Page<CatalogTechnologyResponseDTO> lst = paginationCommons.pagination(pagination);
		return new ResponseEntity<>(lst,HttpStatus.OK);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<CatalogTechnologyResponseDTO>> findAll(){
		List<CatalogTechnologyEntity> unitsMeasureEntities = this.service.getAll();
		List<CatalogTechnologyResponseDTO> unitsMeasureDTO = this.mapper.listEntityToDTO(unitsMeasureEntities);
		return new ResponseEntity<>(unitsMeasureDTO,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CatalogTechnologyResponseDTO> findById(@PathVariable("id") Long id){
		CatalogTechnologyEntity CatalogTechnologyEntity = this.service.readById(id);
		CatalogTechnologyResponseDTO CatalogTechnologyDTO =this.mapper.toGetDTO(CatalogTechnologyEntity);
		return new ResponseEntity<>(CatalogTechnologyDTO,HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<CatalogTechnologyResponseDTO> save(@Validated @RequestBody  CatalogTechnologyRequestDTO dto){
		CatalogTechnologyEntity unitMesEntity = this.service.create( this.mapper.toEntity(dto) );
		CatalogTechnologyResponseDTO CatalogTechnologyDTO =  this.mapper.toGetDTO(unitMesEntity);
		return new ResponseEntity<>(CatalogTechnologyDTO,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CatalogTechnologyResponseDTO> update(@Validated @PathVariable("id") Long id, @RequestBody CatalogTechnologyRequestDTO dto){
		dto.setIdCatalogTechnology(id);
		CatalogTechnologyEntity CatalogTechnologyEntity = this.mapper.toEntity(dto);
		CatalogTechnologyEntity CatalogTechnologyEntityUpdated = this.service.update(CatalogTechnologyEntity, id);
		return new ResponseEntity<>(this.mapper.toGetDTO(CatalogTechnologyEntityUpdated),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		this.service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}

