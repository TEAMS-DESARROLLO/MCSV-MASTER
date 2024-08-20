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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.dto.LeaderResponseDTO;
import com.bussinesdomain.maestros.dto.RegionRequestDTO;
import com.bussinesdomain.maestros.dto.RegionResponseDTO;
import com.bussinesdomain.maestros.mapper.IRegionMapper;
import com.bussinesdomain.maestros.models.RegionEntity;
import com.bussinesdomain.maestros.services.IRegionService;
import com.bussinesdomain.maestros.services.impl.RegionPaginationServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/region")
public class RegionController {

	private final IRegionMapper mapper;
	private final RegionPaginationServiceImpl paginationCommons;
	private final IRegionService service;
	
	
	@PostMapping("/pagination")
	public ResponseEntity<?> paginator(@RequestBody PaginationModel pagination){
		log.info("PAGINATION ..... " + pagination);
		Page<RegionResponseDTO> lst = paginationCommons.pagination(pagination);
		return new ResponseEntity<>(lst,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<RegionResponseDTO>> findAll(){
		List<RegionEntity> regionEntities = this.service.getAll();
		List<RegionResponseDTO> regionDTO = this.mapper.listEntityToDTO(regionEntities);
		return new ResponseEntity<>(regionDTO,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RegionResponseDTO> findById(@PathVariable("id")Long id){
		RegionEntity regionEntity = this.service.readById(id).stream().filter(p->p.getRegistrationStatus().equals("A")).findFirst().orElse(null);
		RegionResponseDTO regionDTO = this.mapper.toGetDTO(regionEntity);
		return new ResponseEntity<>(regionDTO,HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<RegionResponseDTO> save(@Validated @RequestBody RegionRequestDTO dto){
		RegionEntity regionEntity = this.service.create( this.mapper.toEntity(dto));
		RegionResponseDTO regionDTO = this.mapper.toGetDTO(regionEntity);
		return new ResponseEntity<>(regionDTO,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<RegionResponseDTO> save(@Validated @PathVariable("id") Long id,@RequestBody RegionRequestDTO dto){
		dto.setIdRegion(id);
		RegionEntity regionEntity = this.mapper.toEntity(dto);
		RegionEntity regionEntityUpdated = this.service.update(regionEntity, id);
		return new ResponseEntity<>(this.mapper.toGetDTO(regionEntityUpdated), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id ){
		this.service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

    @PostMapping("/regionByIds")
    public ResponseEntity<List<RegionResponseDTO>> communitiesByIds(@RequestParam("ids")  List<Long> ids) {
        
        List<RegionResponseDTO> lista = service.regionsByIds(ids);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }    	
}
