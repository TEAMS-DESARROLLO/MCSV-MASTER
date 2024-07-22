package com.bussinesdomain.maestros.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bussinesdomain.maestros.dto.CommunityResultadoDTO;
import com.bussinesdomain.maestros.dto.CommunityTreeDTO;
import com.bussinesdomain.maestros.dto.TreeNodesDataDTO;
import com.bussinesdomain.maestros.services.impl.TreeComunitySubPracticaTenololgyServiceImpl;

import lombok.RequiredArgsConstructor;

@RequestMapping("/communityTree")
@RestController
@RequiredArgsConstructor
public class ComunityTreeController {

    private final TreeComunitySubPracticaTenololgyServiceImpl treeComunitySubPracticaTenololgyService;


	@GetMapping("/getResultado")
	public ResponseEntity<List<TreeNodesDataDTO>> getTreeComunitySubPracticaTenololgy(){
	
		List<TreeNodesDataDTO> lst = treeComunitySubPracticaTenololgyService.getTreeComunitySubPracticaTenololgy();
		
		return new ResponseEntity<>(lst, HttpStatus.OK);
	}

}
