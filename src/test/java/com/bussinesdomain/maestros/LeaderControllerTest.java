package com.bussinesdomain.maestros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;

import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.controllers.LeaderController;
import com.bussinesdomain.maestros.dto.LeaderDTO;
import com.bussinesdomain.maestros.mapper.ILeaderMapper;
import com.bussinesdomain.maestros.models.LeaderEntity;
import com.bussinesdomain.maestros.models.PracticeEntity;
import com.bussinesdomain.maestros.services.ILeaderService;
import com.bussinesdomain.maestros.services.impl.LeaderPaginationService;


import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

public class LeaderControllerTest {

    @Mock
    private ILeaderService leaderService;

    @Mock
    private ILeaderMapper leaderMapper;

    @Mock
    private LeaderPaginationService leaderPaginationService;  
    
    
    @InjectMocks
    private LeaderController leaderController;



    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }    

    @Test
    public void testFindByFiltro() {
        // Datos simulados
        LeaderEntity leaderEntity = new LeaderEntity();
        leaderEntity.setPractice(new PracticeEntity(1L, "Practice Description"));

        LeaderDTO leaderDTO = new LeaderDTO();
        leaderDTO.setIdPractice(1L);
        leaderDTO.setPracticeDescription("Practice Description");

        when(leaderService.getAll()).thenReturn(Arrays.asList(leaderEntity));
        when(leaderMapper.toGetDTO(leaderEntity)).thenReturn(leaderDTO);

        // Llamar al método y verificar la respuesta
        ResponseEntity<List<LeaderDTO>> response = leaderController.findByFiltro();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(1L, response.getBody().get(0).getIdPractice());
        assertEquals("Practice Description", response.getBody().get(0).getPracticeDescription());
    }   
    
    
    
    @Test
    public void testPaginador() {
        // Datos simulados
        PaginationModel paginationModel = new PaginationModel();
        paginationModel.setPageNumber(0);
        paginationModel.setRowsPerPage(10);

        LeaderDTO leaderDTO = new LeaderDTO();
        leaderDTO.setIdPractice(1L);
        leaderDTO.setPracticeDescription("Practice Description");

        Page<LeaderDTO> page = new PageImpl<>(Arrays.asList(leaderDTO), PageRequest.of(0, 10), 1);

        when(leaderPaginationService.pagination(paginationModel)).thenReturn(page);

        // Llamar al método y verificar la respuesta
        ResponseEntity<?> response = leaderController.paginador(paginationModel);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, ((Page<LeaderDTO>) response.getBody()).getTotalElements());
        assertEquals(1L, ((Page<LeaderDTO>) response.getBody()).getContent().get(0).getIdPractice());
        assertEquals("Practice Description", ((Page<LeaderDTO>) response.getBody()).getContent().get(0).getPracticeDescription());

    }    

}
