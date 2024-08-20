package com.bussinesdomain.maestros.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubpracticaTreeDTO {

    private Long idSpecializacion;
    private String descriptionSpecializacion;  
    private List<TechnologyResponseDTO> tecnologies;    

}
