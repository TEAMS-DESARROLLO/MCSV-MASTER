package com.bussinesdomain.maestros.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data   
@AllArgsConstructor
@NoArgsConstructor
public class SubpracticaResponseDTO {

    private Long idSubpractica;
    private String descriptionSubpractica;  
    private List<TechnologyResponseDTO> tecnologies;


}
