package com.bussinesdomain.maestros.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.bussinesdomain.maestros.dto.TreeNodesDataDTO;
import com.bussinesdomain.maestros.services.impl.TreePracticeSpecializationTenololgyServiceImpl;

import lombok.RequiredArgsConstructor;

@RequestMapping("/communityTree")
@RestController
@RequiredArgsConstructor
public class PracticeTreeController {

    private final TreePracticeSpecializationTenololgyServiceImpl treePracticeSpecializationTenololgyServiceImpl;


	@GetMapping("/getResultado")
	public ResponseEntity<List<TreeNodesDataDTO>> getTreeComunitySubPracticaTenololgy(){
	
		List<TreeNodesDataDTO> lst = treePracticeSpecializationTenololgyServiceImpl.getTreePracticeSpecializationTenololgy();
		
		return new ResponseEntity<>(lst, HttpStatus.OK);
	}

}
